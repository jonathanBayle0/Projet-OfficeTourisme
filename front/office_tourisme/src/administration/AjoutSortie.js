import React, { useState } from "react";
import axios from "axios";
import moment from "moment"

function AjoutSortie() {
    const [sortie, setSortie] = useState({
        nom: "",
        description: "",
        dateDebut: "",
        dateFin: "",
        prix: "",
        adresse: "",
        capacite: ""
    });

    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [errors, setErrors] = useState({});

    const handleChange = (event) => {
        const { name, value } = event.target;
        setSortie({ ...sortie, [name]: value });
        setErrors({ ...errors, [name]: "" });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const dateDebutFormatted = moment(sortie.dateDebut).format("YYYY-MM-DD HH:mm:ss");
            const dateFinFormatted = moment(sortie.dateFin).format("YYYY-MM-DD HH:mm:ss");
            const sortieDto = {
                ...sortie,
                dateDebut: dateDebutFormatted,
                dateFin: dateFinFormatted,
            }
            console.log(sortieDto);
            const head = {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
            };
            const response = await axios.post("/ajout_sortie", sortieDto, head);
            console.log(response);
            // Vérification de la réponse de l'API
            if (response.data.res === true) {
                // Remise a zero des differents champs
                setSortie({
                    nom: "",
                    description: "",
                    dateDebut: "",
                    dateFin: "",
                    prix: "",
                    adresse: "",
                    capacite: "",
                });
                setSuccessMessage("La sortie a bien été ajoutée");
                setErrorMessage("");
                setErrors({})
            } else {
                // Récupération des messages d'erreur
                const errors = response.data.mess;
                // Affichage des messages d'erreur pour chaque champ
                errors.forEach(error => {
                    setErrors(prevState => ({
                        ...prevState,
                        [error.field]: error.mess
                    }));
                });
                setSuccessMessage("");
            }
        } catch (error) {
            console.error("Error enregistrement sortie ", error);
            setErrorMessage("Erreur : impossible d'enregistrer la sortie.");
            setSuccessMessage("");
        }
    };

    return (
        <div>
            <h1>Ajouter une sortie </h1>
            <form onSubmit={handleSubmit}>
                <label>
                    Nom :
                    <input
                        type="text"
                        name="nom"
                        value={sortie.nom}
                        onChange={handleChange}
                    />
                </label>
                {errors.nom && <div className="error">{errors.nom}</div>}
                <label>
                    Description :
                    <textarea
                        name="description"
                        value={sortie.description}
                        onChange={handleChange}
                    />
                </label>
                {errors.description && <div className="error">{errors.description}</div>}
                <label>
                    Date de début :
                    <input
                        type="datetime-local"
                        name="dateDebut"
                        value={sortie.dateDebut}
                        onChange={handleChange}
                    />
                </label>
                {errors.dateDebut && <div className="error">{errors.dateDebut}</div>}
                <label>
                    Date de fin :
                    <input
                        type="datetime-local"
                        name="dateFin"
                        value={sortie.dateFin}
                        onChange={handleChange}
                    />
                </label>
                {errors.dateFin && <div className="error">{errors.dateFin}</div>}
                <label>
                    Prix :
                    <input
                        type="number"
                        name="prix"
                        value={sortie.prix}
                        onChange={handleChange}
                    />
                </label>
                {errors.prix && <div className="error">{errors.prix}</div>}
                <label>
                    Adresse :
                    <input
                        type="text"
                        name="adresse"
                        value={sortie.adresse}
                        onChange={handleChange}
                    />
                </label>
                {errors.adresse && <div className="error">{errors.adresse}</div>}
                <label>
                    Capacite :
                    <input
                        type="number"
                        name="capacite"
                        value={sortie.capacite}
                        onChange={handleChange}
                    />
                </label>
                {errors.capacite && <div className="error">{errors.capacite}</div>}
                {errorMessage && <div className="error">{errorMessage}</div>}
                {successMessage && <div className="success">{successMessage}</div>}
                <button type="submit">Ajouter la sortie</button>
            </form>
        </div>
    );
}

export default AjoutSortie;
