import { useState } from "react";
import {register} from "../services/RegisterService";

function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [buttonHover, setButtonHover] = useState(false);
    const [message, setMessage] = useState("");

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await register(username, password);
            console.log('User registered:', response.data);
            setMessage('Registration successful!');
            // Redirect the user to another page or update the state to indicate successful registration
        } catch (error) {
            console.error('Error registering user:', error);
            setMessage('Registration failed. Please try again.');
            // Show an error message to the user or update the state to indicate a registration error
        }
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
                <h1 style={styles.label}>Register</h1>
                <input
                    style={styles.input}
                    type="text"
                    name="username"
                    value={username}
                    onChange={handleUsernameChange}
                    placeholder="Username"
                    required
                />
                <input
                    style={styles.input}
                    type="password"
                    name="password"
                    value={password}
                    onChange={handlePasswordChange}
                    placeholder="Password"
                    required
                />

                {message && (
                    <div>
                        <p>{message}</p>
                    </div>
                )}
                <button
                    style={
                        buttonHover ? { ...styles.button, ...styles.buttonHover } : styles.button
                    }
                    type="submit"
                    onMouseOver={() => setButtonHover(true)}
                    onMouseOut={() => setButtonHover(false)}
                >
                    Register
                </button>
            </form>
        </div>
    );
}
export default Register;







