import React, { useEffect, useState } from 'react'
import { Route, Routes, useLocation } from 'react-router-dom'
import { isLogged, isAdmin } from './authentification.js'
import { AdminRouterGuard } from './administration/AdminRouterGuard.js'
import { UserRouterGuard } from './utilisateur/UserRouterGuard.js'

import Menu from './Menu.js'
import Accueil from './Accueil.js'
import Connexion from './Connexion.js'
import Inscription from './Inscription.js'
import AccueilAdmin from './administration/AccueilAdmin.js'
import AjoutSortie from './administration/AjoutSortie.js'
import GestionSortie from './administration/GestionSortie.js'
import ModificationSortie from './administration/ModificationSortie.js'
import AffichageSorties from './AffichageSorties.js'
import DetailSortie from './DetailSortie.js'
import AffichagePanier from './utilisateur/AffichagePanier.js'
import AffichageHistorique from './utilisateur/AffichageHistorique.js'

function App() {
  const [connecte, setConnecte] = useState(false);
  const [role, setRole] = useState(false);

  const location = useLocation();

  useEffect(() => {
    setConnecte(isLogged());
    setRole(isAdmin());
  }, [location]);


  const handleConnecteChange = (newValue) => {
    setConnecte(newValue);
  };

  const handleRoleChange = (newValue) => {
    setRole(newValue);
  };

  return (
    <div>
      <Menu connecte={connecte} admin={role} />
      <Routes>
        <Route exact path="/" element={<Accueil />} />
          <Route path="/sortie" element={<AffichageSorties />} />
          <Route path="/sortie/detailSortie/:sortieId" element={<DetailSortie />} />
        <Route exact path="/Connexion" element={<Connexion onConnecteChange={handleConnecteChange} onRoleChange={handleRoleChange} />} />
        <Route exact path="/Inscription" element={<Inscription />} />
        {/* Routes administrateurs */}
        <Route element={<AdminRouterGuard />}>
          <Route path="/admin/*" element={<AccueilAdmin />} />
          <Route path="/admin/sortie/AjoutSortie" element={<AjoutSortie />} />
          <Route path="/admin/sortie/GestionSortie" element={<GestionSortie />} />
          <Route path="/admin/sortie/ModificationSortie/:sortieId" element={<ModificationSortie />} />
        </Route>
        {/* Routes utilisateur connecte */}
        <Route element={<UserRouterGuard />}>
          <Route path="/user/*" element={<Accueil />} />
          <Route path="/user/panier" element={<AffichagePanier />} />
          <Route path="/user/historique" element={<AffichageHistorique />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
