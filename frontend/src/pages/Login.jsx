import { useState } from "react";

function Login() {
    const [nombre, setNombre] = useState("");
    const [mensaje, setMensaje] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(`http://localhost:8080/api/usuarios/nombre/${nombre}`);
            if (response.ok) {
                const data = await response.json();
                setMensaje(`Bienvenido ${data.nombre} 🎉`);
            } else {
                setMensaje("Usuario no encontrado ❌");
            }
        } catch (error) {
            console.error("Error al iniciar sesión:", error);
            setMensaje("Error de conexión con el servidor ⚠️");
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <label>
                    Nombre:
                    <input
                        type="text"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                        required
                    />
                </label>
                <button type="submit">Ingresar</button>
            </form>
            {mensaje && <p>{mensaje}</p>}
        </div>
    );
}

export default Login;
