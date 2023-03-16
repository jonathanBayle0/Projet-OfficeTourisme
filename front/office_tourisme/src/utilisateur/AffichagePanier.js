import axios from "axios";
import React, { useEffect, useState } from "react";
import { AfficherSortie } from "../Sortie";

function AffichagePanier() {
    const [sorties, setSorties] = useState([]);
    const compteId = localStorage.getItem("id")

    const triSortieParDate = (sortiesListe) => {
        return [...sortiesListe].sort((a, b) => new Date(a.dateDebut) - new Date(b.dateDebut));
    };

    function suppSortie(panierId) {
        if (window.confirm("Êtes-vous sûr de vouloir supprimer cette activité du panier ?")) {
            axios({
                method: 'post',
                url: '/supprimer_panier',
                headers: { 'Content-Type': 'application/json', "Authorization": "Bearer " + localStorage.getItem("token") },
                data: {
                    panierId,
                },
            }).then((response) => {
                if (response.data.res) {
                    window.location.reload()
                }
            });
        }
    }
    
    function handleValidation() {
        if (window.confirm("Êtes-vous sûr de vouloir valider votre panier ?")) {
            axios({
                method: 'post',
                url: '/valider_panier',
                headers: { 'Content-Type': 'application/json', "Authorization": "Bearer " + localStorage.getItem("token") },
                data: {
                    compteId
                },
            }).then((response) => {
                if (response.data.res) {
                    window.location.reload()
                }
            });
        }

    }

    async function getPanier() {
        axios({
            method: "post",
            url: "/recuperer_panier",
            headers: { "Content-Type": "application/json" },
            data: {
                compteId
            }
        }).then((response) => {
            const sortiesBDD = response.data.panier.map((sortie) => {
                // Creation du bouton de suppression
                let panier = sortie.panier.find(p => parseInt(p.compteId) == compteId)
                let suppression = (
                    <button onClick={() => suppSortie(panier.id)}>
                        <i className="fa fa-trash"></i>
                    </button>
                );

                return { nom: sortie.nom, description: sortie.description, dateDebut: sortie.dateDebut, dateFin: sortie.dateFin, prix: sortie.prix, adresse: sortie.adresse, capacite: sortie.capacite, suppression };
            });

            setSorties(triSortieParDate(sortiesBDD));
        });
    }

    useEffect(() => {
        getPanier()
    }, []);

    const columns = [
        "Nom",
        "Description",
        "Date de début",
        "Date de fin",
        "Prix",
        "Adresse",
        "Capacité",
        "Supprimer"
    ];

    // Rendu si le panier est vide ou non
    if (sorties.length == 0) {
        return (
            <div>
                <h2>Votre panier est vide</h2>
            </div>
        );
    } else {
        return (
            <div>
                <h2>Votre panier :</h2>
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
                <br />
                <button onClick={handleValidation}>Valider la sélection</button>
            </div>
        );
    }
}

export default AffichagePanier;