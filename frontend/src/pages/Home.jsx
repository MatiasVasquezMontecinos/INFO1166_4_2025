import { useEffect, useState } from "react";
import { getUsuarios } from "../services/api";

export default function Home() {
    const [usuarios, setUsuarios] = useState([]);

    useEffect(() => {
        getUsuarios()
            .then((res) => setUsuarios(res.data))
            .catch((err) => console.error(err));
    }, []);

    return (
        <div style={{ padding: "2rem" }}>
            <h2>Home</h2>
            <h3>Usuarios registrados:</h3>
            <ul>
                {usuarios.map((u) => (
                    <li key={u.id}>{u.nombre}</li>
                ))}
            </ul>
        </div>
    );
}
