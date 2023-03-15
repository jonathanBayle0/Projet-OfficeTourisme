import axios from "axios";
import React, { useEffect, useState } from "react";
import { AfficherSortie } from "../Sortie";

function GestionSortie() {
    const [sorties, setSorties] = useState([]);

    useEffect(() => {
        axios({
            method: "get",
            url: "/recuperer_sorties",
            headers: { "Content-Type": "application/json" },
        }).then((response) => {
            const sorties = response.data.sorties.map((sortie) => {
                // Creation de l'url de modification
                let id = sortie.id
                let edition = (<a href={`/admin/sortie/ModificationSortie/${id}`}><i className="fa fa-edit"></i></a>);
                return { nom: sortie.nom, description: sortie.description, dateDebut: sortie.dateDebut, dateFin: sortie.dateFin, prix: sortie.prix, adresse: sortie.adresse, capacite: sortie.capacite, edition };
            });
            setSorties(sorties);
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
        "Edition"
    ];

    return (
        <div>
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
                        <>
                            <AfficherSortie sortie={sortie} />
                        </>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default GestionSortie;
