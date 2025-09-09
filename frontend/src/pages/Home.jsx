import { Link } from "react-router-dom";

function Home() {
    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h1>Hola Mundo 👋</h1>
            <p>Bienvenido a la Bolsa Nacional de Trabajo</p>
            <div style={{ marginTop: "20px" }}>
                <Link to="/registro">
                    <button style={{ marginRight: "10px" }}>Ir a Registro</button>
                </Link>
                <Link to="/login">
                    <button>Ir a Login</button>
                </Link>
            </div>
        </div>
    );
}

export default Home;
