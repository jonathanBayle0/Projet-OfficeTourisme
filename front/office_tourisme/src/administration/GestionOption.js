import { useState, useEffect } from "react";
import axios from "axios";

function GestionOption(props) {
    const [options, setOptions] = useState([])
    const [sujet, setSujet] = useState('');
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
    
    function suppOption(optionId) {
        if (window.confirm("Êtes-vous sûr de vouloir supprimer cette option ?")) {
            axios({
                method: 'post',
                url: '/supprimer_option',
                headers: { 'Content-Type': 'application/json', "Authorization": "Bearer " + localStorage.getItem("token") },
                data: {
                    sortieId,
                    optionId,
                },
            })
            .then((response) => {
                setOptions(options.filter(option => option.id !== optionId))
            })
        }
    }

    function handleSujetChange(e) {
        setSujet(e.target.value);
    }

    function addOption(e) {
        e.preventDefault();
        axios({
            method: 'post',
            url: '/ajouter_option',
            headers: { 'Content-Type': 'application/json', "Authorization": "Bearer " + localStorage.getItem("token") },
            data: {
                sortieId,
                sujet,
            },
        })
        .then((response) => {
            setOptions([...options, response.data.option]);
            setSujet('');
        })
        .catch((err) => {
            
        })
    }
    
    return (
        <div>
          {options.map((option) => (
              <div key={option.id} className="container">
              <p>{option.sujet}</p>
              <button onClick={() => suppOption(option.id)}>
                <i className="fa fa-trash"></i>
              </button>
            </div>
          ))}

          <h3>Ajout d'une option</h3>
          <form onSubmit={addOption}>
            <label>Sujet:</label>
            <input type="text" name="sujet" value={sujet} onChange={handleSujetChange} />
            <button type="submit">Ajouter</button>
        </form>
        </div>
      );      
}

export default GestionOption;