import { useState } from "react";

function GenerarCv() {
    const [userId, setUserId] = useState("");
    const [tipo, setTipo] = useState("pdf");
    const [mensaje, setMensaje] = useState("");

    const generarCv = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/cv/${tipo}/${userId}`, {
                method: "GET"
            });

            const data = await response.text();
            setMensaje(data);
        } catch (error) {
            setMensaje("Error al generar el CV: " + error.message);
        }
    };

    return (
        <div className="p-4 border rounded max-w-md mx-auto">
            <h2 className="text-xl font-bold mb-4">Generar CV</h2>

            <input
                type="text"
                placeholder="ID del usuario"
                value={userId}
                onChange={(e) => setUserId(e.target.value)}
                className="border px-2 py-1 rounded w-full mb-3"
            />

            <select
                value={tipo}
                onChange={(e) => setTipo(e.target.value)}
                className="border px-2 py-1 rounded w-full mb-3"
            >
                <option value="pdf">PDF</option>
                <option value="word">Word</option>
            </select>

            <button
                onClick={generarCv}
                className="bg-blue-600 text-white px-4 py-2 rounded"
            >
                Generar
            </button>

            {mensaje && (
                <div className="mt-4 p-2 border rounded bg-gray-100">
                    <p>{mensaje}</p>
                </div>
            )}
        </div>
    );
}

export default GenerarCv;
