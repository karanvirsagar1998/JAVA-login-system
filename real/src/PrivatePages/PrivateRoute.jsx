import React from 'react'
import { Outlet, Navigate } from 'react-router-dom'
import { isLoggedIn } from '../auth/index'

function PrivateRoute() {
    return isLoggedIn() ? <Outlet /> : <Navigate to={"/"} />
}

export default PrivateRoute