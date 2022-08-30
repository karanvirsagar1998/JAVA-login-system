import React from "react";
import { Link } from "react-router-dom";
function Forgot() {
    return (
        <div className="App">
            <header>
                <h2>Collabushare</h2>
            </header>
            <main>
                <h3>Forgot your Password?</h3>
                <p>Enter your email address below and we will send you a link to reset your password.</p>
                <form>
                    <table>
                        <tbody>
                            <tr><td><label>Email Address</label></td></tr>
                            <tr><td><input type="text" name="username" placeholder="Enter Username" /></td></tr>
                            <tr><td><input type="submit" value="Submit" /></td></tr>
                        </tbody>
                    </table>
                </form>
            </main>
            <div className="below">
                <Link to="/">Back To Welcome Team</Link>
            </div>
        </div>
    );
}

export default Forgot;
