import { useEffect, useState } from "react";
import axios from "axios";

function Statistique() {
    const [sorties, setSorties] = useState([]);
    const [comptes, setComptes] = useState([]);
    const [reservation, setReservation] = useState([]);


    useEffect(() => {
        // Recuperation de toutes les sorties
        axios({
            method: "get",
            url: "/recuperer_sorties",
            headers: { "Content-Type": "application/json" },
        }).then((response) => {
            const sortiesBDD = response.data.sorties
            setSorties(sortiesBDD);
        });
        
        // Recuperation de toutes les sorties
        axios({
            method: "get",
            url: "/recuperer_comptes",
            headers: { "Content-Type": "application/json" },
        }).then((response) => {
            let comptesBDD = response.data.comptes
            // On retire l'administrateur de la liste
            comptesBDD.splice(0, 1)
            console.log(comptesBDD);
            setComptes(comptesBDD);
        });
    }, []);

    useEffect(() => {
        let reserv = 0
        sorties.forEach((sortie) => reserv += sortie.historique.length)
        setReservation(reserv)
    }, [sorties])

    return (
        <div className="liste">
        <h1>Statistique</h1>
        <ul>
            <li>Nombre de compte : {comptes.length}</li>
            <li>Nombre de sortie : {sorties.length}</li>
            <li>Nombre de réservation : {reservation}</li>
        </ul>
        </div>
    )
}

export default Statistique;