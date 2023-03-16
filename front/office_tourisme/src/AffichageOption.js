import { useState, useEffect } from "react";
import axios from "axios";

function AffichageOption(props) {
    const [options, setOptions] = useState([])
    const sortieId = props.sortieId


    // Initialisation des options
    useEffect(() => {
        const getData = async () => {
            axios({
                method: 'post',
                url: '/recuperer_options',
                headers: { 'Content-Type': 'application/json' },
                data: {
                    id: sortieId,
                },
            })
                .then((response) => {
                    setOptions(response.data.options)
                })
        }
        getData();
    }, []);

    if (options.length === 0) {
        return (
            <div>
                <p>Aucune option pour cette sortie</p>
            </div>
        );
    } else {
        return (
            <div>
                <ul>
                {options.map((option) => (
                    <div key={option.id} className="container">
                        <li>{option.sujet}</li>
                        
                    </div>
                ))}
                </ul>
            </div>
        );
    }
}

export default AffichageOption;