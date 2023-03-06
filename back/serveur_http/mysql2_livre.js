const mysql2 = require('mysql2/promise')
const config = require('./config.js')
const livre = require('./livre.js')


async function enregistrer_livre(livre) {
    let correct = true

    Object.keys(livre).forEach(attr => {
        if (typeof attr === 'undefined') correct = false
    })

    if (correct) {
        let titre = livre.titre
        let auteur = livre.auteur
        let annee = livre.annee
        var sql = `INSERT INTO t_livre (titre, auteur, annee) VALUES ('${titre}','${auteur}',${annee})`;

        const pool = mysql2.createPool(config.mysql2_pool)

        try {
            await pool.execute(sql)
            console.log("Livre correctement inséré")
        } catch (error) {
            console.log("Erreur le livre n'a pas pu être inséré");
            console.log(error);
        }
    }
}


module.exports = {
    enregistrer_livre: enregistrer_livre
}