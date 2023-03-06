const mysql2 = require('mysql2/promise')

const config = require('./config.js')

const pool = mysql2.createPool(config.mysql2_pool)

async function enregistrer_livre(obj, resolve, reject) {
    console.log("enregistrer livre " + JSON.stringify(obj))

    let titre = obj.titre
    let auteur = obj.auteur
    let annee = obj.annee
    let sql = `INSERT INTO t_livre (titre, auteur, annee) VALUES ('${titre}','${auteur}',${annee})`

    try {
        const [res] = await pool.execute(sql)
        console.log("res = " + JSON.stringify(res))
        resolve({ res: true, mess: "Le livre " + obj.titre + " a été enregistré " })
    }
    catch (err) {
        reject({ res: false, mess: err })
    }
}

async function lister_livre(resolve, reject) {
    console.log("lister livre ")

    let sql = `SELECT * FROM t_livre`
    console.log("sql = " + sql);

    try {
        const [livres] = await pool.execute(sql)
        console.log("lister_livre OK")
        resolve({ res: true, livres: livres })
    }
    catch (err) {
        console.log("Erreur lister_livre " + err);
        reject({ res: false , mess: err})
    }
}
module.exports = {
    enregistrer_livre,
    lister_livre
}