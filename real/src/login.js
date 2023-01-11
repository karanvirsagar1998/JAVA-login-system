import { React, useState, useEffect } from "react";
import { Link, Navigate } from "react-router-dom";
import './mystyle.css'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { loginUser } from "../src/services/user-service"
import { doLogin, isLoggedIn } from "../src/auth/index";

export default function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {
        if (isLoggedIn()) {
            window.location.href = 'http://localhost:3000/user/dash'
        }
    })


    function sendLoginRequest() {
        const reqBody = {
            username: username,
            password: password,
        };

        if (reqBody.username !== '' && reqBody.password !== '') {
            loginUser(reqBody).then((token) => {
                alert("Success..")
                doLogin(token, () => {
                    console.log("Token set to local storage")
                });
                return window.location.href = 'http://localhost:3000/user/dash'
            }).catch(err => {
                console.log(err.response.data.token)
                alert(err.response.data.token)
            })
        } else {
            alert("Username or Password is empty !!")
        }
    }
    return (
        <>
            <div className="App">
                <header>
                    <h2>Login System</h2>
                </header>
                <main>
                    <h3>Login</h3>
                    <p>Enter your email address and password to login.</p>
                    <form>
                        <table>
                            <tbody>
                                <tr><td><label><b>Email Address</b></label></td></tr>
                                <tr>
                                    <td><input type="text"
                                        name="email"
                                        id="email"
                                        placeholder="Enter Email Address"
                                        onChange={(e) => setUsername(e.target.value)}
                                        value={username}
                                    />
                                    </td>
                                </tr><tr><td><label><b>Password</b></label>(<Link to="/forgot">forgot?</Link>)</td></tr>
                                <tr>
                                    <td>
                                        <input type="password"
                                            name="password"
                                            id="password"
                                            placeholder="Enter Password"
                                            onChange={(e) => setPassword(e.target.value)}
                                            value={password}
                                        />
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="button" onClick={() => sendLoginRequest()} value="Sign In" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </main>
            </div>
        </>
    );
}
