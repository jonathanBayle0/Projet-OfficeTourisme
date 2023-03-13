import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { isLogged, isAdmin } from '../authentification.js';

function AdminRouterGuard() {
  const connecte = isLogged();
  const admin = isAdmin();

  return (
    (connecte && admin) ? <Outlet /> : <Navigate to="/Connexion" />
  )
}

export { AdminRouterGuard };
