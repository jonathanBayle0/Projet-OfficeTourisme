const express = require('express')     // chargement du module express
const config = require("./config.js") // chargement de la configuration
const util = require("util")
const validation = require('./validation.js')
const bd = require('./bd.js')
const jwt = require('jsonwebtoken')

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

    let ident = req.body.ident
    let mdp = req.body.mdp

    if ((ident == "ubo") && (mdp == "1234")) {


        let token = jwt.sign({
            data: {
                ident: ident,
                role: "admin",
            }
        }, private_key, { expiresIn: 60 * 60 }); // 60 minutes

        res.send({ res: true, token: token, role: "admin", mess: "Succès, connexion réussie" })
    } else {
        res.send({ res: false, mess: "Connexion impossible" })
    }

})
