import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home.jsx";
import GenerarCv from "./components/GenerarCv.jsx";
import PerfilUsuario from "./components/PerfilUsuario.jsx";
import Navbar from "./components/Navbar.jsx";

function App() {
    return (
        <Router>
            <Navbar />

            <div className="p-6">
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/GenerarCv" element={<GenerarCv />} />
                    <Route path="/PerfilUsuario" element={<PerfilUsuario />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
