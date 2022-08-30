import React from 'react'
import { doLogout } from "../auth/index"
function UserDashboard() {

    function logoutUser(e) {
        e.preventDefault();
        if (doLogout()) {
            alert("Logged out");
            window.location.href = 'http://localhost:3000/'
        }
    }

    return (
        <div>UserDashboard
            <input type="button" onClick={logoutUser} value="Logout" />
        </div>
    )
}

export default UserDashboard