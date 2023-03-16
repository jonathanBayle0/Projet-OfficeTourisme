import { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import { GetSortie } from "../Sortie";
import { isLogged } from '../authentification';
import axios from "axios";
import moment from "moment"

function ChoixSortie() {
    let { sortieId } = useParams();

    // Champs de sortie qu'on souhaite recuperer
    const [sortie, setSortie] = useState({});

    // Initialisation des champs de sortie
    useEffect(() => {
        const getData = async () => {
            const s = await GetSortie(sortieId)
            setSortie(prevState => ({
                ...prevState,
                id: s.id,
                nom: s.nom,
                description: s.description,
                dateDebut: s.dateDebut,
                dateFin: s.dateFin,
                prix: s.prix,
                adresse: s.adresse,
                capacite: s.capacite
            }));
        }
        getData();
    }, []);

    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const dateDebutFormatted = moment(sortie.dateDebut).format("YYYY-MM-DD HH:mm:ss");
            const dateFinFormatted = moment(sortie.dateFin).format("YYYY-MM-DD HH:mm:ss");
            const prix = sortie.prix.toString()
            const capacite = sortie.capacite.toString()
            const id = sortie.id.toString()
            const sortieDto = {
                ...sortie,
                dateDebut: dateDebutFormatted,
                dateFin: dateFinFormatted,
                id,
                prix,
                capacite
            }
            // Construction du panier
            const data = {
                sortieId: sortieDto.id,
                compteId: localStorage.getItem("id")
            }
            const head = {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
            };
            const response = await axios.post("/ajout_sortie_panier", data, head);
            // Vérification de la réponse de l'API
            if (response.data.res === true) {
                setSuccessMessage("La sortie est bien ajoutée au panier");
                setErrorMessage("");
            } else {
                // Récupération des messages d'erreur
                setErrorMessage(response.data.mess);
                setSuccessMessage("");
            }
        } catch (error) {
            console.error("Error ajout sortie dans le panier ", error);
            setErrorMessage("Erreur : impossible d'enregistrer la sortie dans le panier.");
            setSuccessMessage("");
        }
    };

    return (
        <div>
            <h3>Détail de la sortie :</h3>
            <div>
                <label>Nom :</label>
                <span>{sortie.nom}</span>
            </div>
            <div>
                <label>Description :</label>
                <span>{sortie.description}</span>
            </div>
            <div>
                <label>Date de début :</label>
                <span>{sortie.dateDebut}</span>
            </div>
            <div>
                <label>Date de fin :</label>
                <span>{sortie.dateFin}</span>
            </div>
            <div>
                <label>Prix :</label>
                <span>{sortie.prix}</span>
            </div>
            <div>
                <label>Adresse :</label>
                <span>{sortie.adresse}</span>
            </div>
            <div>
                <label>Capacite :</label>
                <span>{sortie.capacite}</span>
            </div>
            {errorMessage && <div className="error">{errorMessage}</div>}
            {successMessage && <div className="success">{successMessage}</div>}
            { isLogged() && <button type="button" onClick={handleSubmit}>Ajouter au panier</button>}
            <br />
            {/* <h3>Gérer les options :</h3>
            <GestionOption sortieId={sortieId} /> */}
            <h2>Commentaires :</h2>

        </div>
    );

}

export default ChoixSortie;