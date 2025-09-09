import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createUsuario } from "../services/api";

export default function Login() {
    const [nombre, setNombre] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createUsuario({ nombre });
            navigate("/"); // ir al Home
        } catch (error) {
            alert("Error al crear usuario: " + error.response?.data || error.message);
        }
    };

    return (
        <div style={{ padding: "2rem" }}>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Nombre"
                    value={nombre}
                    onChange={(e) => setNombre(e.target.value)}
                    required
                />
                <button type="submit">Ingresar</button>
            </form>
        </div>
    );
}
