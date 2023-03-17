import React, { useState } from 'react';
import axios from 'axios';

function Inscription() {
    const [compte, setCompte] = useState({
        nom: "",
        prenom: "",
        mail: "",
        mdp: ""
    })

    const [error, setError] = useState({
        mess: '',
        nom: '',
        prenom: '',
        mail: '',
        mdp: ''
    })

    const [succes, setSucces] = useState('');

    function handleChange(event) {
        setCompte((prevState) => ({
            ...prevState,
            [event.target.name]: event.target.value,
        }));
    }

    function handleSubmit(event) {
        event.preventDefault();
        console.log('Inscription');
        console.log('compte = ' + JSON.stringify(compte));
        axios({
            method: 'post',
            url: '/inscription',
            headers: { 'Content-Type': 'application/json' },
            data: compte,
        })
            .then((res) => {
                console.log(JSON.stringify(res));
                // Remise a zero des messages precedents
                setSucces('');
                setError({
                    mess: '',
                    nom: '',
                    prenom: '',
                    mail: '',
                    mdp: ''
                });
                // Inscription reussie
                if (res.data.res) {
                    setSucces(res.data.mess);
                } else {
                    // Erreur non gere
                    console.error(res);
                }
                // Gestion des erreurs
            })
            .catch((err) => {
                // Remise a zero des messages precedents
                setSucces('');
                setError({
                    mess: '',
                    nom: '',
                    prenom: '',
                    mail: '',
                    mdp: ''
                });
                // Erreur syntaxique du mot de passe et/ou l'adresse mail
                if (err.response.status === 404) {
                    let data = err.response.data;
                    console.log(data);
                    setError((prevState) => {
                        const newState = { ...prevState };
                        Object.keys(newState).forEach((key) => {
                            data.mess[1].forEach((error) => {
                                if (error.field === key) {
                                    newState[key] = error.mess;
                                }
                            });
                        });
                        return newState;
                    });
                    // Erreur de saisie des champs
                } else if (err.response.status === 400) {
                    setError({
                        ...error,
                        mess: err.response.data.mess,
                    });
                }
                console.error(err);
            });
    }

    return (
        <div>
          <h2>Inscription</h2>
          {succes && <div className="error-msg">{succes}</div>}
          {error.mess && <div className="error-msg">{error.mess}</div>}
          <form>
            <div>
              <label htmlFor="nom">Nom :</label>
              <input
                type="text"
                id="nom"
                name="nom"
                value={compte.nom}
                onChange={handleChange}
              />
              {error.nom && <div className="error-msg">{error.nom}</div>}
            </div>
            <div>
              <label htmlFor="prenom">Prénom :</label>
              <input
                type="text"
                id="prenom"
                name="prenom"
                value={compte.prenom}
                onChange={handleChange}
              />
              {error.prenom && <div className="error-msg">{error.prenom}</div>}
            </div>
            <div>
              <label htmlFor="mail">Email :</label>
              <input
                type="email"
                id="mail"
                name="mail"
                value={compte.mail}
                onChange={handleChange}
              />
              {error.mail && <div className="error-msg">{error.mail}</div>}
            </div>
            <div>
              <label htmlFor="mdp">Mot de passe :</label>
              <input
                type="password"
                id="mdp"
                name="mdp"
                value={compte.mdp}
                onChange={handleChange}
              />
              {error.mdp && <div className="error-msg">{error.mdp}</div>}
            </div>
            <button type="submit" onClick={handleSubmit}>
              S'inscrire
            </button>
          </form>
        </div>
      );      
}
export default Inscription