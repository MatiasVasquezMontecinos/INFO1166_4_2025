import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav style={{ padding: "1rem", background: "#333", color: "#fff" }}>
            <Link to="/" style={{ color: "#fff", marginRight: "1rem" }}>Home</Link>
            <Link to="/GenerarCv" style={{ color: "#fff" }}>Generar Cv</Link>
            <Link to="/PerfilUsuario" style={{ color: "#fff", marginLeft: "1rem" }}>Perfil Usuario</Link>
        </nav>
    );
}
