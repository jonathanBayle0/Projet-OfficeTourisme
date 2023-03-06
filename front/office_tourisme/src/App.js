import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'

import Accueil from './Accueil.js'
import Connexion from './Connexion.js'
import Inscription from './Inscription.js'


class App extends React.Component {

  constructor(props) {
    super(props)

  }

  render() {
    return (
      <div>
        <Router>
          <Routes>
            <Route exact path="/" element={<Accueil />} />
            <Route exact path="/Connexion" element={<Connexion />} />
            <Route exact path="/Inscription" element={<Inscription />} />
          </Routes>

        </Router>
      </div>



    )
  }
}

export default App
