const validation = require("./validation.js")

class Sortie {
    id =null
    nom = null
    description = null
    dateDebut = null
    dateFin = null
    prix = null
    adresse = null
    capacite = null

    constructor(obj) {
        validation.init_attributs(this, obj)
        console.log("Sortie = " + JSON.stringify(this))
    }
}


const donnees_validation = [
    // Le Champs description n'est pas obligatoire
    {
        field: "nom",
        regexp: ".{2,}",
        mess: "Le nom doit comporter au moins 2 caractères",
    },
    {
        field: "dateDebut",
        regexp: ".{11,}",
        mess: "Il faut au moins 11 caractères pour la date de début",
    },
    {
        field: "dateFin",
        regexp: ".{11,}",
        mess: "Il faut au moins 11 caractères pour la date de fin",
    },
    {
        field: "prix",
        regexp: ".{1,}",
        mess: "Il faut au moins 1 chiffre pour le prix",
    },
    {
        field: "capacite",
        regexp: ".{1,}",
        mess: "Il faut au moins 1 chiffre pour la capacité",
    },
    {
        field: "adresse",
        regexp: ".{8,}",
        mess: "Il faut au moins 8 caractères pour l'adresse",
    },
]

module.exports = {
    Sortie: Sortie,
    donnees_validation: donnees_validation,
}

