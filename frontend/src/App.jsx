import { Routes, Route, Link } from "react-router-dom";
import Registro from "./pages/Registro.jsx";
import Login from "./pages/Login.jsx";
import Home from "./pages/Home.jsx";

function App() {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/registro" element={<Registro />} />
            </Routes>
        </div>
    );
}

export default App;
