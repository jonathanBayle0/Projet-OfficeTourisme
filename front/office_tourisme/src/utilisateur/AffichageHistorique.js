import axios from "axios";
import React, { useEffect, useState } from "react";
import { AfficherSortie } from "../Sortie";

function AffichageHistorique() {
    const [sorties, setSorties] = useState([]);
    const compteId = localStorage.getItem("id")

    const triSortieParDate = (sortiesListe) => {
        return [...sortiesListe].sort((a, b) => new Date(a.dateDebut) - new Date(b.dateDebut));
    };

    async function getHistorique() {
        axios({
            method: "post",
            url: "/recuperer_historique",
            headers: { "Content-Type": "application/json" },
            data: {
                compteId
            }
        }).then((response) => {
            const sortiesBDD = response.data.historique.map((sortie) => {
                return { nom: sortie.nom, description: sortie.description, dateDebut: sortie.dateDebut, dateFin: sortie.dateFin, prix: sortie.prix, adresse: sortie.adresse, capacite: sortie.capacite };
            });

            setSorties(triSortieParDate(sortiesBDD));
        });
    }

    useEffect(() => {
        getHistorique()
    }, []);

    const columns = [
        "Nom",
        "Description",
        "Date de début",
        "Date de fin",
        "Prix",
        "Adresse",
        "Capacité",
    ];

    // Rendu si l'historique est vide ou non
    if (sorties.length == 0) {
        return (
            <div>
                <h2>Votre historique est vide</h2>
            </div>
        );
    } else {
        return (
            <div>
                <h2>Votre historique :</h2>
                <table>
                    <thead>
                        <tr>
                            {columns.map((column) => (
                                <th key={column}>{column}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {sorties.map((sortie, index) => (
                            <AfficherSortie key={index} sortie={sortie} />
                        ))}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default AffichageHistorique;