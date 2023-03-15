import React, { useState } from "react";

function AfficherSortie(props) {
  const [sortie, setSortie] = useState(props.sortie);

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

export { AfficherSortie };
