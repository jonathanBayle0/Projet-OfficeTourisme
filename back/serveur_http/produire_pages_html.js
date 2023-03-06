const fs = require('fs')

const base = require('./config.js').base

const modele = "html_src/modele.html"

function produire_page(titre, fic_srce, fic_dest) {

    console.log("produire_page " + titre)

    let page = fs.readFileSync(base + "/" + modele, { encoding: 'utf8' })

    let contenu = fs.readFileSync(base + "/" + fic_srce, { encoding: 'utf8' })

    page = page.replace(/<!-- titre -->/g, titre)
    page = page.replace(/<!-- contenu -->/, contenu)
    //console.log("page = "+page)
    fs.writeFileSync(base + "/" + fic_dest, page, { encoding: 'utf8' })
}

produire_page("Accueil", "html_src/src_accueil.html", "html/index.html")
produire_page("Liste des livres", "html_src/src_livre_lister.html", "html/livre_lister.html")
produire_page("Enregistrement d'un livre", "html_src/src_livre_enregistrer.html", "html/livre_enregistrer.html")
produire_page("Modification d'un livre", "html_src/src_livre_modifier.html", "html/livre_modifier.html")
produire_page("Connexion", "html_src/src_connexion.html", "html/connexion.html")
produire_page("Déconnexion", "html_src/src_deconnexion.html", "html/deconnexion.html")

