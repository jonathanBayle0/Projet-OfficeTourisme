const validation = require("./validation.js")

class Inscription {

    nom = null
    prenom = null
    mail = null
    mdp = null

    constructor(obj) {
        validation.init_attributs(this, obj)
        console.log("Inscription = " + JSON.stringify(this))
    }
}


const donnees_validation = [
    {
        field: "nom",
        regexp: ".{3,}",
        mess: "Il faut au moins 3 caractères pour le nom",
    },
    {
        field: "prenom",
        regexp: ".{3,}",
        mess: "Il faut au moins 3 caractères pour le prenom",
    },
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
    Inscription: Inscription,
    donnees_validation: donnees_validation,
}

