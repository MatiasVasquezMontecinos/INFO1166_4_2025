import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav style={{ padding: "1rem", background: "#333", color: "#fff" }}>
            <Link to="/" style={{ color: "#fff", marginRight: "1rem" }}>Home</Link>
            <Link to="/login" style={{ color: "#fff" }}>Login</Link>
        </nav>
    );
}
