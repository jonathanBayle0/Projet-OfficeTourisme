import React, { useState } from 'react';
import axios from 'axios';
import { enregistrerToken, isLogged, isAdmin } from './authentification';

function Connexion(props) {
    const [compte, setCompte] = useState({
        mail: '',
        mdp: '',
    });
    const [error, setError] = useState({
        mess: '',
        mail: '',
        mdp: '',
    });
    const [succes, setSucces] = useState('');

    function handleChange(event) {
        setCompte((prevState) => ({
            ...prevState,
            [event.target.name]: event.target.value,
        }));
    }

    function connexion() {
        console.log('connexion');
        console.log('compte = ' + JSON.stringify(compte));
        console.log(JSON.stringify(compte));
        axios({
            method: 'post',
            url: '/connexion',
            headers: { 'Content-Type': 'application/json' },
            data: compte,
        })
            .then((res) => {
                console.log(JSON.stringify(res));
                // Remise a zero des messages precedents
                setSucces('');
                setError({
                    mess: '',
                    mail: '',
                    mdp: '',
                });
                // Connexion reussie
                if (res.data.res) {
                    setSucces(res.data.mess);
                    console.log(res.data.token);
                    enregistrerToken(
                        res.data.token,
                        res.data.expiration,
                        res.data.role
                    );
                    // Mise a jour de l'etat de connexion
                    props.onConnecteChange(isLogged());
                    props.onRoleChange(isAdmin());
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
                    mail: '',
                    mdp: '',
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
                    // Erreur de connexion - mot de passe ou email incorrect
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
            <h2>Connexion</h2>
            <div className='succes-msg'>{succes}</div>
            <div className='error-msg'>{error.mess}</div>
            <table><tbody>
                <tr>
                    <td>Email : </td>
                    <td><input name="mail" type="email" value={compte.mail} onChange={handleChange} /></td>
                    <td className='error-msg'>{error.mail}</td>

                </tr>
                <tr>
                    <td>Mot de passe : </td>
                    <td><input name="mdp" type="password" value={compte.mdp} onChange={handleChange} /></td>
                    <td className='error-msg'>{error.mdp}</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><button className="btn-connexion" onClick={connexion}>Connexion</button></td>
                </tr>
            </tbody></table>
        </div>
    )
}
export default Connexion