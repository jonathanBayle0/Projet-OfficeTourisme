import React, { useState } from "react";
import axios from "axios";
import moment from "moment"

const AjoutSortie = () => {
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

    const handleChange = (event) => {
        const { name, value } = event.target;
        setSortie({ ...sortie, [name]: value });
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
                    "Authorization": "Bearer " + sessionStorage.getItem("token")
                },
            };
            const response = await axios.post("/ajout_sortie", sortieDto, head);
            console.log(response);
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
            setSuccessMessage("Succes ! La sortie a bien été ajouté");
        } catch (error) {
            console.error("Error enregistrement sortie ", error);
            setErrorMessage("Erreur : impossible d'enregistrer la sortie.");
        }
    };

    return (
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
            <br />
            <label>
                Description :
                <textarea
                    name="description"
                    value={sortie.description}
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Date de début :
                <input
                    type="datetime-local"
                    name="dateDebut"
                    value={sortie.dateDebut}
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Date de fin :
                <input
                    type="datetime-local"
                    name="dateFin"
                    value={sortie.dateFin}
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Prix :
                <input
                    type="number"
                    name="prix"
                    value={sortie.prix}
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Adresse :
                <input
                    type="text"
                    name="adresse"
                    value={sortie.adresse}
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Capacite :
                <input
                    type="number"
                    name="capacite"
                    value={sortie.capacite}
                    onChange={handleChange}
                />
            </label>
            <br />
            {errorMessage && <div>{errorMessage}</div>}
            {successMessage && <div>{successMessage}</div>}
            <button type="submit">Ajouter la sortie</button>
        </form>
    );
};

export default AjoutSortie;
