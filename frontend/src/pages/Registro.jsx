import { useState } from "react";

function Registro() {
    const [nombre, setNombre] = useState("");
    const [mensaje, setMensaje] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/api/usuarios", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ nombre }), // 👈 envías los datos al backend
            });

            if (response.ok) {
                const data = await response.json();
                setMensaje(`Usuario ${data.nombre} creado con éxito ✅`);
                setNombre("");
            } else {
                setMensaje("Error: No se pudo registrar el usuario ⚠️");
            }
        } catch (error) {
            console.error("Error al registrar:", error);
            setMensaje("Error de conexión con el servidor ❌");
        }
    };

    return (
        <div>
            <h2>Registro de Usuario</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Nombre:
                    <input
                        type="text"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                        required
                    />
                </label>
                <button type="submit">Registrar</button>
            </form>
            {mensaje && <p>{mensaje}</p>}
        </div>
    );
}

export default Registro;
