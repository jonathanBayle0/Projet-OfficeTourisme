import { useEffect, useState } from "react";
import axios from "axios";

function GestionComptes() {
    const [comptes, setComptes] = useState([]);

    function suppCompte(compteId) {
        if (window.confirm("Êtes-vous sûr de vouloir supprimer ce compte ? L'action est irréversible")) {
            axios({
                method: 'post',
                url: '/supprimer_compte',
                headers: { 'Content-Type': 'application/json', "Authorization": "Bearer " + localStorage.getItem("token") },
                data: {
                    compteId
                },
            })
            .then((response) => {
                window.location.reload()
            })
        }
    }

    useEffect(() => {
        // Recuperation de toutes les comptes
        axios({
            method: "get",
            url: "/recuperer_comptes",
            headers: { "Content-Type": "application/json" },
        }).then((response) => {
            // On recupere uniquement les informations utiles
            let comptesBDD = response.data.comptes.map((cpt) => {
                let id = cpt.id
                // Creation du bouton de suppression
                let suppression = (
                    <button onClick={() => suppCompte(id)}>
                        <i className="fa fa-trash"></i>
                    </button>
                );

                return { nom: cpt.nom, prenom: cpt.prenom, mail: cpt.mail, suppression }
            })
            // On retire l'administrateur de la liste
            comptesBDD.splice(0, 1)
            console.log(comptesBDD);
            setComptes(comptesBDD);
        });
    }, []);

    const columns = [
        "Nom",
        "Prenom",
        "Email",
        "Suppression"
    ];

    return (
        <div>
            <h1>Gestion des comptes</h1>
            <table>
                <thead>
                    <tr>
                        {columns.map((column) => (
                            <th key={column}>{column}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {comptes.map((compte) => (
                        <tr>
                            {Object.keys(compte).map((key) => (
                                <>
                                    <td>{compte[key]}</td>
                                </>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default GestionComptes;