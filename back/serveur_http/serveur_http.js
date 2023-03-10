const express = require('express')     // chargement du module express
const config = require("./config.js") // chargement de la configuration
const util = require("util")
const validation = require('./validation.js')
const jwt = require('jsonwebtoken')
const compte = require('./compte.js')
const sortie = require('./sortie.js')

const { private_key } = require('./config')
const { resolve } = require('path')
const { type } = require('os')
const { response } = require('express')

const base = config.base
const port_http = config.port_http

var app = express()
app.use("/", express.static(base + "/html", { index: 'index.html' }))
app.use(express.json({ limit: '50mb' }))
app.use(express.urlencoded({ extended: true }))

var server = app.listen(port_http, function () {
    console.log('Express server listening on port ' + port_http)
})

app.post("/ajout_sortie", function (req, res) {
    console.log("POST ajout_sortie")
    // Verification des droits 
    const authHeader = req.headers.authorization
    if (!authHeader) {
        res.status(401).send({ res: false, mess: "Erreur : il faut être authentifié pour effectuer cette action" })
        return;
    }
    let token = authHeader.split(' ')[1]
    const connecte = verify_token(token)
    if (! connecte.res) {
        res.status(401).send({ res: false, mess: connecte.err })
    }

    let s = new sortie.Sortie(req.body)
    console.log("Objet sortie créé : " + JSON.stringify(s))

    let mess = "Objet sortie créé : " + JSON.stringify(s)

    let v = validation.valider_attributs(s, sortie.donnees_validation)
    console.log(s);
    if (v.res) {
        // Enregistrement de la sortie en Base de donnees
        fetch('http://localhost:8080/sorties', {
            headers: {
                "Content-Type": "application/json",
            },
            method: "POST",
            body: JSON.stringify(s)
        })
        .then((response) => {
            if (!response.ok) {
                console.log("Erreur : " + JSON.stringify(response));
            } else {
                console.log("Réussi : " + JSON.stringify(response));
            }

        })
        .catch((err) => {
            console.log("Erreur ! " + JSON.stringify(err));
        })
    }
    else {
        mess += "<br>Echec validation"
    }

    res.send({ res: v.res, mess: mess })
})

app.post("/connexion", function (req, res) {
    console.log("POST connexion")
    console.log("req = " + util.inspect(req))

    let cpt = new compte.Compte(req.body)
    console.log("Objet compte créé : " + JSON.stringify(cpt))

    let v = validation.valider_attributs(cpt, compte.donnees_validation)

    // Verification des bons parametres d'entree
    if (v.res) {

        // On recupere les informations de la base de donnes pour la verification
        fetch('http://localhost:8080/comptes/mail/' + cpt.mail)
            .then((response) => {
                // Traitement si l'email n'existe pas
                if (!response.ok) {
                    res.status(400).send({ res: false, mess: "Connexion impossible, l'email n'existe pas !" })
                    return;
                }
                return response.json();
            })
            // Traitement si l'email existe
            .then((json) => {
                let compteBDD = json;
                // Verification du mot de passe
                if (cpt.mdp === compteBDD.mdp) {
                    // Creation du token
                    const expiresIn = 60 * 60 // 60 minutes
                    let token = jwt.sign({
                        data: {
                            mail: compteBDD.mail,
                            role: compteBDD.statut,
                        }
                    }, private_key, { expiresIn: expiresIn });
                    const expirationDate = new Date(Date.now() + expiresIn * 1000);

                    res.send({ res: true, token: token, role: compteBDD.statut, expiration: expirationDate, mess: "Succès, connexion réussie" })
                } else {
                    // Si le mot de passe ne correspond pas
                    res.status(400).send({ res: false, mess: "Connexion impossible, mot de passe incorrect" })
                }
            })
            .catch((error) => {
                console.error(error);
            });
    } else {
        let mess = ["Erreur : Identifiant de connexion erroné"]
        mess.push(v.lmess)
        res.status(404).send({ res: false, mess: mess })
    }

})


function verify_token(token) {
    try {
        let decoded = jwt.verify(token, private_key)

        // Verification de l'expiration du jeton
        let exp = (decoded.exp * 1000) - Date.now()
        if (exp < Date.now()) {
            return { res: false, err: "Erreur : Jeton expiré" }
        }

        return { res: true, data: decoded.data }

    } catch (e) {
        console.log("Erreur jwt.verify " + e)
        return { res: false, err: "Erreur : Jeton incorrect" }
    }
}