import axios from "axios";
import React, { useEffect, useState } from "react";
import { AfficherSortie } from "../Sortie";

function AffichageSorties() {
    const [sorties, setSorties] = useState([]);

    const triSortieParDate = (sorties) => {
        return [...sorties].sort((a, b) => new Date(a.dateDebut) - new Date(b.dateDebut));
    };

    useEffect(() => {
        axios({
            method: "get",
            url: "/recuperer_sorties",
            headers: { "Content-Type": "application/json" },
        }).then((response) => {
            const sorties = response.data.sorties.map((sortie) => {
                // Creation de l'url de modification
                let id = sortie.id;
                let edition = (
                    <a href={`/user/sortie/choixSortie/${id}`}>
                        <i className="fa fa-search"></i>
                    </a>
                );
                return { nom: sortie.nom, description: sortie.description, dateDebut: sortie.dateDebut, dateFin: sortie.dateFin, prix: sortie.prix, adresse: sortie.adresse, capacite: sortie.capacite, edition };
            });
            setSorties(triSortieParDate(sorties));
        });
    }, []);

    const columns = [
        "Nom",
        "Description",
        "Date de début",
        "Date de fin",
        "Prix",
        "Adresse",
        "Capacité",
        "Détail"
    ];

    return (
        <div>
            <h2>Liste des sorties</h2>
            <table>
                <thead>
                    <tr>
                        {columns.map((column) => (
                            <th key={column}>{column}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {sorties.map((sortie) => (
                        <AfficherSortie key={sortie.id} sortie={sortie} />
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default AffichageSorties;
