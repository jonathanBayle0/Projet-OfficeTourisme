import React, { useState } from "react";
import axios from "axios";

function AfficherSortie(props) {
  const [sortie] = useState(props.sortie);

  const keys = Object.keys(sortie);

  return (
    <tr>
      {keys.map((key) => (
        <>
          <td>{sortie[key]}</td>
        </>
      ))}
    </tr>
  );
}

async function GetSortie(id) {
  try {
    const response = await axios({
      method: "post",
      url: "/recuperer_sortie",
      headers: { "Content-Type": "application/json" },
      data: { id },
    });
    return response.data.sortie;
  } catch (error) {
    console.error(error);
    throw error; // renvoyer l'erreur pour qu'elle puisse être gérée en dehors de la fonction
  }
}

export { AfficherSortie, GetSortie };
