import { redirect } from 'react-router-dom'

function enregistrerToken(token, expiration, role) {
    localStorage.setItem("token", token)
    localStorage.setItem("expiration", expiration)
    localStorage.setItem("role", role)
}

function deconnexion() {
    localStorage.removeItem("token")
    localStorage.removeItem("expiration")
    localStorage.removeItem("role")

    // Mise a jour de l'etat de connexion
    this.props.updateLogin(false);
    return redirect("/");
}

function isLogged() {
    const token = localStorage.getItem("token");
    const expiration = localStorage.getItem("expiration");

    // Verification du jeton et de sa date d'expiration
    if (!token || !expiration) {
        return false;
    }

    // Verification de l'expiration du jeton
    const expirationDate = new Date(expiration);
    if (expirationDate <= new Date()) {
        return false;
    }

    return true;
}

function isAdmin() {
    const role = localStorage.getItem("role")
    if (role === "A") return true
    return false
}

export {
    enregistrerToken,
    deconnexion,
    isLogged,
    isAdmin,
}