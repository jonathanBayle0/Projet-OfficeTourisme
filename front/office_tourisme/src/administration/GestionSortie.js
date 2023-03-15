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
            const edition = (<a href="/"><i className="fa fa-edit"></i></a>);
            const sorties = response.data.sorties.map(({ nom, description, dateDebut, dateFin, prix, adresse, capacite }) => {
                return { nom, description, dateDebut, dateFin, prix, adresse, capacite, modification: edition };
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
