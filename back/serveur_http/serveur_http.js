const express = require('express')     // chargement du module express
const config = require("./config.js") // chargement de la configuration
const util = require("util")
const validation = require('./validation.js')
const bd = require('./bd.js')
const jwt = require('jsonwebtoken')
const compte = require('./compte.js')

const { private_key } = require('./config')
const { resolve } = require('path')
const { type } = require('os')

const base = config.base
const port_http = config.port_http

var app = express()
app.use("/", express.static(base + "/html", { index: 'index.html' }))
app.use(express.json({ limit: '50mb' }))
app.use(express.urlencoded({ extended: true }))

var server = app.listen(port_http, function () {
    console.log('Express server listening on port ' + port_http)
})

app.post("/enregistrer_livre", function (req, res) {

    // const authHeader = req.headers.authorization
    // let token;
    // if (authHeader) {
    //     token = authHeader.split(' ')[1]
    //     console.log("token = " + token)
    // }

    // let ident
    // let role
    // try {
    //     let decoded = jwt.verify(token, private_key)

    //     console.log("TOKEN");
    //     console.log(decoded.data);
    //     ident = decoded.data.ident
    //     role = decoded.data.role

    //     let exp = (decoded.exp * 1000) - Date.now()
    //     // temps restant en ms

    // } catch (e) {
    //     console.log("Erreur jwt.verify " + e)
    // }


    console.log("POST enregistrer_livre")
    // console.log("req = " + util.inspect(req))
    console.log("titre = " + req.body.titre)
    console.log("auteur = " + req.body.auteur)
    console.log("annee = " + req.body.annee)

    let l = new livre.Livre(req.body)
    console.log("Objet livre créé : " + JSON.stringify(l))

    let mess = "Objet livre créé : " + JSON.stringify(l)

    let v = validation.valider_attributs(l, livre.donnees_validation)
    if (v.res) { // + && ident == "ubo" && role == "admin" pour l'identification
        mess += "<br>La validation des champs a été effectuée avec succès"
        mess += "<br>Livre à enregistrer en BD : "
        mess += `<br>Titre = ${l.titre}`
        mess += `<br>Auteur = ${l.auteur}`
        mess += `<br>Année = ${l.annee}`

        console.log("Enregistrement dans la base de donnée")
        var promise = new Promise((resolve, reject) => {
            bd.enregistrer_livre(l, resolve, reject)
        })

        promise.then(() => {
            console.log("Livre correctement inséré")
        })
            .catch((err) => {
                console.log(err);
            })
    }
    else {
        mess += "<br>Echec validation"
    }
    // if (typeof ident !== 'undefined' && typeof role !== 'undefined') {
    // } else {
    //     mess += "<br>Echec validation"
    //     mess += "<br>Connexion requise"
    // } 

    res.send({ res: v.res, mess: mess, lerr: v.lmess })
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
                    const expiresIn = 60 * 2 // 2 minutes
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
