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
        return;
    }
    
    // Verification si l'utilisateur est bien administrateur
    if (connecte.data.role !== "A") {
        res.status(401).send({ res: false, mess: "Erreur : il faut être admnistrateur pour effectuer cette action" })
        return;
    }
    console.log(req.body);
    let s = new sortie.Sortie(req.body)
    console.log("Objet sortie créé : " + JSON.stringify(s))

    let mess;

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
                v.res = false
                mess = "Erreur"
            } else {
                console.log("Réussi : " + JSON.stringify(response));
                mess = "Succès, la sortie a été ajouté"
            }
            res.send({ res: v.res, mess: mess })
        })
        .catch((err) => {
            console.log("Erreur ! " + JSON.stringify(err));
        })
    }
    else {
        mess = v.lmess
        res.send({ res: v.res, mess: mess })
    }
    
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
                            id: compteBDD.id
                        }
                    }, private_key, { expiresIn: expiresIn });
                    const expirationDate = new Date(Date.now() + expiresIn * 1000);

                    res.send({ res: true, token: token, role: compteBDD.statut, id: compteBDD.id, expiration: expirationDate, mess: "Succès, connexion réussie" })
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

// Recuperation de toutes les sorties
app.get("/recuperer_sorties", function(req, res) {
    fetch('http://localhost:8080/sorties')
    .then((response) => {
        return response.json();
    })
    .then((sorties) => {
        console.log(sorties);
        res.send({ res: true, sorties })
    })
    .catch((err) => {
        console.log(err);
        res.status(400).send({ res: false })
    })
})


// Recuperation d'une seule sortie
app.post("/recuperer_sortie", function(req, res) {
    const id = req.body.id
    fetch('http://localhost:8080/sorties/' + id)
    .then((response) => {
        return response.json();
    })
    .then((sortie) => {
        console.log(sortie);
        res.send({ res: true, sortie })
    })
    .catch((err) => {
        console.log(err);
        res.status(400).send({ res: false })
    })
})

// Recuperation d'une seule sortie
app.post("/recuperer_options", function(req, res) {
    console.log("GET recuperer option");
    const id = req.body.id
    fetch('http://localhost:8080/options/sortie/' + id)
    .then((response) => {
        return response.json();
    })
    .then((options) => {
        console.log(options);
        res.send({ res: true, options })
    })
    .catch((err) => {
        console.log(err);
        res.status(400).send({ res: false })
    })
})

// Suppression d'une option
app.post("/supprimer_option", function(req, res) {
    console.log("DELETE option");
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
        return;
    }
    
    // Verification si l'utilisateur est bien administrateur
    if (connecte.data.role !== "A") {
        res.status(401).send({ res: false, mess: "Erreur : il faut être admnistrateur pour effectuer cette action" })
        return;
    }

    const sortieId = req.body.sortieId
    const optionId = req.body.optionId
    const sortieOption = {
        "sortieId": sortieId,
        "optionId": optionId
    }
    fetch('http://localhost:8080/sortieOptions/sortie/', {
        headers: {
            "Content-Type": "application/json",
        },
        method: "delete",
        body: JSON.stringify(sortieOption)
    })
    .then((response) => {
        return response.json();
    })
    .then((response) => {
        console.log(response);
        if (response) {
            res.send({ res: true })
        } else {
            res.send({ res: false })
        }
    })
    .catch((err) => {
        console.log(err);
        res.status(400).send({ res: false })
    })
})

app.post("/ajouter_option", function(req, res) {
    console.log("AJOUT option");
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
        return;
    }
    
    // Verification si l'utilisateur est bien administrateur
    if (connecte.data.role !== "A") {
        res.status(401).send({ res: false, mess: "Erreur : il faut être admnistrateur pour effectuer cette action" })
        return;
    }

    const sortieId = req.body.sortieId
    const sujet = req.body.sujet

    // Ajout de l'option
    fetch('http://localhost:8080/options', {
        headers: {
            "Content-Type": "application/json",
        },
        method: "post",
        body: JSON.stringify({ sujet })
    })
    .then((response) => {
        return response.json();
    })
    .then((option) => {
        console.log(option);
        // Liaison entre l'option et la sortie
        fetch('http://localhost:8080/sortieOptions', {
            headers: {
                "Content-Type": "application/json",
            },
            method: "post",
            body: JSON.stringify({
                sortieId,
                optionId: option.id,
            })
        })
        .then((response) => {
            return response.json();
        })
        .then((response) => {
            console.log(response);
            // Ontransmet l'option qui sera ajoutée dans la liste
            res.send({ res: true, option })
        })
        .catch((err) => {
            console.log(err);
            res.status(400).send({ res: false, err })
        })
    })
    .catch((err) => {
        console.log(err);
        res.status(400).send({ res: false, err })
    })
})


function verify_token(token) {
    try {
        let decoded = jwt.verify(token, private_key)

        // Verification de l'expiration du jeton
        const exp = decoded.exp * 1000
        const now = new Date().getTime();
        if (exp < now) {
            return { res: false, err: "Erreur : Jeton expiré" }
        }

        return { res: true, data: decoded.data }

    } catch (e) {
        console.log("Erreur jwt.verify " + e)
        return { res: false, err: "Erreur : Jeton incorrect" }
    }
}