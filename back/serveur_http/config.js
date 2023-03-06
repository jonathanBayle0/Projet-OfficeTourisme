const fs = require('fs')

const base = "C:/Users/john-/OneDrive/Documents/Etude/M1/S8/Projet/Application/back/serveur_http"
const port_http = 4000

if (!fs.existsSync(base)) {
    console.log("Erreur chargement config.js")
    console.log("Le dossier " + base + " n'existe pas")
    console.log("Modifier la variable base")
    process.exit(0)
}

const mysql2_pool = {
    host: "localhost",
    user: "root",
    password: "",
    database: "officeTourisme",
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0
}

const private_key = "zjerYhe+7V"

module.exports = {
    base : base,
    port_http : port_http,
    mysql2_pool: mysql2_pool,
    private_key: private_key,
}