const mysql2 = require('mysql2/promise')

const config = require('./config.js')

async function test(resolve, reject) {
    console.log("test")

    const connection = await mysql2.createConnection(config.mysql2)

    let sql = "select * from t_livre"

    const [livres] = await connection.execute(sql)

    connection.end()

    resolve(livres)
}

new Promise((resolve, reject) => {
    test(resolve, reject)
})
.then((data) => {
    console.log("Liste des livres :")
    data.forEach((l) => {
        console.log(`id_livre = ${l.id_livre} titre = ${l.titre} auteur = ${l.auteur} année = ${l.annee}`)
    })
})
.catch((err) => {
    console.log("Erreur " + err)
})



