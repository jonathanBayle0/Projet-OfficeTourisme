import { deconnexion } from './authentification';
import './Main.css'

function Menu(props) {

    // Si l'utilisateur est connecte
    if (props.connecte) {
        // Si il est administrateur
        if (props.admin) {
            return (
                <div className="menu">
                    <h2>Menu</h2>
                    <ul>
                        <li><a href="/admin/Accueil">Accueil</a></li>
                        <li><a href="/admin/sortie/AjoutSortie">Ajouter sortie</a></li>
                        <li><a href="/admin/sortie/GestionSortie">Gestion des sorties</a></li>
                        <li><a href="/admin/compte/GestionComptes">Gestion des comptes</a></li>
                        <li><a href="/admin/Statistique">Statistique</a></li>
                        <li onClick={() => deconnexion()}><a href="/">Déconnexion</a></li>
                    </ul>
                </div>
            );
            // Sinon utilisateur classique
        } else {
            return (
                <div className="menu">
                    <h2>Menu</h2>
                    <ul>
                        <li><a href="/">Accueil</a></li>
                        <li><a href="/sortie">Sorties</a></li>
                        <li><a href="/user/panier">Panier</a></li>
                        <li><a href="/user/historique">Historique</a></li>
                        <li onClick={() => deconnexion()}><a href="/">Déconnexion</a></li>
                    </ul>
                </div>
            );
        }
    }
    // Sinon utilisateur non-connecte
    return (
        <div className="menu">
            <h2>Menu</h2>
            <ul>
                <li><a href="/">Accueil</a></li>
                <li><a href="/sortie">Sorties</a></li>
                <li><a href="/Connexion">Connexion</a></li>
                <li><a href="/Inscription">Inscription</a></li>
            </ul>
        </div>
    );
}

export default Menu;