import { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import { GetSortie } from "./Sortie";
import { isLogged } from './authentification';
import AffichageOption from './AffichageOption';
import axios from "axios";
import moment from "moment"

function DetailSortie() {
    let { sortieId } = useParams();
    const compteId = localStorage.getItem("id")

    const [sortie, setSortie] = useState({});
    const [commentaires, setCommentaires] = useState([]);
    const [comment, setComment] = useState('');

    // Initialisation des champs de la sortie
    useEffect(() => {
        const getData = async () => {
            // Recuperation de la sortie
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

            const c = await axios({
                method: "post",
                url: "/recuperer_commentaires",
                headers: { "Content-Type": "application/json" },
                data: {
                    sortieId
                }
            })
            setCommentaires(c.data.commentaires)
        }
        getData();
    }, []);

    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");

    // Enregistrement dans le panier
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
                compteId
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

    // Ajout d'un commentaire
    async function handleSubmitCommentaire(event) {
        event.preventDefault();
        if (comment.length === 0) return;
        // Construction du message
        const date = moment().format("YYYY-MM-DD HH:mm:ss") 
        const com = {
            type: "texte",
            date,
            contenu: comment,
            compteId,
            sortieId
        }
        const head = {
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
        };
        const response = await axios.post("/ajout_commentaire", com, head);
        if (response.data.res) {
            setCommentaires([...commentaires, com])
        }
        setComment('')
      };
    
      const handleChangeCommentaire = (event) => {
        setComment(event.target.value);
      };

    const columns = [
        "Date",
        "Message"
    ];

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
            {errorMessage && <div className="error-msg">{errorMessage}</div>}
            {successMessage && <div className="success-msg">{successMessage}</div>}
            {isLogged() && <button type="button" onClick={handleSubmit}>Ajouter au panier</button>}
            <br />
            <h3>Liste des options :</h3>
            <AffichageOption sortieId={sortieId} />
            <h2>Commentaires :</h2>
            {isLogged() &&
                <>
                    <h4>Ajoutez un commentaire : </h4>
                    <form onSubmit={handleSubmitCommentaire}>
                        <label>
                            Commentaire :
                            <textarea value={comment} onChange={handleChangeCommentaire} />
                        </label>
                        <button type="submit">Valider</button>
                    </form>
                </>

            }
            <table>
                <thead>
                    <tr>
                        {columns.map((column) => (
                            <th key={column}>{column}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {commentaires.map((commentaire) => (
                        <>
                        <tr>
                            <td key={commentaire.id}>{commentaire.date}</td>
                            <td key={commentaire.id}>{commentaire.contenu}</td>
                        </tr>
                        </>
                    ))}
                </tbody>
            </table>

        </div>
    );

}

export default DetailSortie;