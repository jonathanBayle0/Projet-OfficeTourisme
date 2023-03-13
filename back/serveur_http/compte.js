const validation = require("./validation.js")

class Compte {

    mail = null
    mdp = null

    constructor(obj) {
        validation.init_attributs(this, obj)
        console.log("compte = " + JSON.stringify(this))
    }
}


const donnees_validation = [
    {
        field: "mail",
        regexp: "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$",
        mess: "L'adresse email est incorrect",
    },
    {
        field: "mdp",
        regexp: ".{4,}",
        mess: "Il faut au moins 4 caractères pour le mot de passe",
    },
]

module.exports = {
    Compte: Compte,
    donnees_validation: donnees_validation,
}

