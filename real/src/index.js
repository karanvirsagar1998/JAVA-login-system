import React from 'react';
import ReactDOM from 'react-dom/client';
import Login from "./login";
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Forgot from "./forgot";
import PrivateRoute from './PrivatePages/PrivateRoute';
import UserDashboard from './UserPages/UserDashboard';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <BrowserRouter>
            <Routes>
                <Route exact path="/" element={[<Login />]} />
                <Route exact path="/forgot" element={[<Forgot />]} />
                <Route exact path="/user" element={[<PrivateRoute />]} >
                    <Route exact path="dash" element={[<UserDashboard />]} />
                </Route>
            </Routes>
        </BrowserRouter>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
