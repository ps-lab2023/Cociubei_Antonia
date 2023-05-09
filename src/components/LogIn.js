import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { logIn } from "../services/LogInService";

const LogIn = ({ usrLogg }) => {
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        logIn(username, password).then((res) => {
            console.log(res.data);
            if (res.data.rol === "ADMIN") {
                //usrLogg(res.data.id);
                // used to persist the user's role between different pages or sessions of the web application.
                localStorage.setItem("rol", JSON.stringify(res.data));
                localStorage.setItem("id", JSON.stringify(res.data.id));
                navigate("/admin");
            } else if (res.data.rol === "CLIENT") {
                //usrLogg(res.data.id);
                localStorage.setItem("rol", JSON.stringify("CLIENT"));
                localStorage.setItem("id", JSON.stringify(res.data.id));
                navigate("/client");
            } else {
                alert("Invalid credentials!");
                setUsername("");
                setPassword("");
            }
        });
    };

    const styles = {
        container: {
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            height: "100vh",
        },
        form: {
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
            padding: "50px", // increase the padding to make the form bigger
            backgroundColor: "#f0f0f0",
            borderRadius: "10px",
            boxShadow: "0px 2px 10px rgba(0, 0, 0, 0.3)",
        },
        label: {
            margin: "0 0 20px 0",
            fontSize: "2em",
        },
        input: {
            width: "100%",
            padding: "10px",
            marginBottom: "20px",
            borderRadius: "5px",
            border: "none",
        },
        button: {
            width: "100%",
            padding: "10px",
            backgroundColor: "#16a085",
            color: "#fff",
            borderRadius: "5px",
            border: "none",
            cursor: "pointer",
            transition: "all 0.3s ease",
        },
        buttonHover: {
            transform: "scale(1.05)",
            backgroundColor: "#1abc9c",
        },
    };

    return (
        <div style={styles.container}>
            <form style={styles.form} onSubmit={handleSubmit}>
                <h1 style={styles.label}>Login</h1>
                <input
                    style={styles.input}
                    type="text"
                    name="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    placeholder="Username"
                    required
                />
                <input
                    style={styles.input}
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Password"
                    required
                />
                <button
                    style={
                        styles.button
                    }
                    type="submit"
                >
                    Log In
                </button>
            </form>
        </div>
    );
};

export default LogIn;


