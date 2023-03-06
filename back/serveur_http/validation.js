function init_attributs(obj, http) { // initialisation des attributs d'un objet
    // on va intialiser obj à partir de http en vérifiant
    // que l'initialisation est possible

    Object.keys(obj).forEach(attr => {
        if (typeof http[attr] === 'undefined') {
            console.log("Warning, le champs " + attr + "n'est pas définie");
            obj[attr] = ""
        }
        else if (typeof http[attr] !== 'string') {
            console.log("Warning, le champs " + attr + "n'est pas une chaine");
            obj[attr] = ""
        }
        else {
            // on initialise l'attribut
            obj[attr] = http[attr]
            // obj[attr] = http[attr].trim() => permet d'enlever les espaces
        }
    })
}

function valider_attributs(obj, donnees_validation) {
    let res = true
    let lmess = []

    donnees_validation.forEach(validation => {
        let r = new RegExp(validation.regexp)
        if(! obj[validation.field].match(r)) {
            res = false
            lmess.push({field: validation.field, mess:validation.mess})
        }
    })

    return ({ res: res, lmess: lmess })
}

module.exports = {
    init_attributs: init_attributs,
    valider_attributs: valider_attributs,
}
