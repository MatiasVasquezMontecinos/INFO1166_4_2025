import { useEffect, useState } from "react";
import axios from "axios";

export default function CondicionLaboral({ userId }) {
    const [condicion, setCondicion] = useState({
        situacionLaboral: "",
        fechaInicioSituacion: "",
        ultimoSalarioLiquido: "",
        ultimaActividad: "",
        buscandoEmpleo: false,
    });

    const [loading, setLoading] = useState(true);
    const [mensaje, setMensaje] = useState("");

    // Cargar datos desde backend
    useEffect(() => {
        axios
            .get(`http://localhost:8080/api/usuarios/${userId}`)
            .then((res) => {
                if (res.data.condicionLaboral) {
                    setCondicion(res.data.condicionLaboral);
                }
            })
            .catch((err) => console.error(err))
            .finally(() => setLoading(false));
    }, [userId]);

    // Manejar cambios en el formulario
    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setCondicion((prev) => ({
            ...prev,
            [name]: type === "checkbox" ? checked : value,
        }));
    };

    // Enviar actualización
    const handleSubmit = (e) => {
        e.preventDefault();
        axios
            .put(`http://localhost:8080/api/usuarios/${userId}/condicion-laboral`, condicion)
            .then(() => setMensaje("Condición laboral actualizada"))
            .catch(() => setMensaje("Error al actualizar"));
    };

    if (loading) return <p>Cargando...</p>;

    return (
        <div className="condicion-laboral">
            <h2>Condición Laboral</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Situación Laboral:
                    <input
                        type="text"
                        name="situacionLaboral"
                        value={condicion.situacionLaboral}
                        onChange={handleChange}
                    />
                </label>

                <label>
                    Fecha Inicio:
                    <input
                        type="date"
                        name="fechaInicioSituacion"
                        value={condicion.fechaInicioSituacion}
                        onChange={handleChange}
                    />
                </label>

                <label>
                    Último Salario Líquido:
                    <input
                        type="number"
                        name="ultimoSalarioLiquido"
                        value={condicion.ultimoSalarioLiquido}
                        onChange={handleChange}
                    />
                </label>

                <label>
                    Última Actividad:
                    <input
                        type="text"
                        name="ultimaActividad"
                        value={condicion.ultimaActividad}
                        onChange={handleChange}
                    />
                </label>

                <label>
                    Buscando Empleo:
                    <input
                        type="checkbox"
                        name="buscandoEmpleo"
                        checked={condicion.buscandoEmpleo}
                        onChange={handleChange}
                    />
                </label>

                <button type="submit">Guardar</button>
            </form>
            {mensaje && <p>{mensaje}</p>}
        </div>
    );
}
