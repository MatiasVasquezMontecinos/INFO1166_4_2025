import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home.jsx";
import Login from "./pages/Login.jsx";
import GenerarCv from "./components/GenerarCv.jsx";

function App() {
    return (
        <Router>
            <nav className="p-4 bg-gray-800 text-white flex gap-4">
                <Link to="/">Home</Link>
                <Link to="/login">Login</Link>
                <Link to="/cv">Generar CV</Link>
            </nav>

            <div className="p-6">
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/cv" element={<GenerarCv />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
