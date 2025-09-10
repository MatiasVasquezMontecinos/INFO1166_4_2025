import { useState, useEffect } from "react";
import axios from "axios";

export default function PerfilUsuario({ userId }) {
    const [usuario, setUsuario] = useState(null);
    const [loading, setLoading] = useState(true);
    const [editMode, setEditMode] = useState({});
    const [formData, setFormData] = useState({});
    const [mensaje, setMensaje] = useState("");

    const tabs = [
        { key: "general", label: "Datos Generales" },
        { key: "condicion", label: "Condición Laboral" },
        { key: "resumen", label: "Resumen" },
        { key: "expectativa", label: "Expectativa" },
        { key: "experiencias", label: "Experiencias" },
        { key: "referencias", label: "Referencias" },
        { key: "educacion", label: "Educación" },
        { key: "capacitaciones", label: "Capacitaciones" },
        { key: "idiomas", label: "Idiomas" },
    ];
    const [activeTab, setActiveTab] = useState("general");

    // Traer datos del usuario
    useEffect(() => {
        axios
            .get(`http://localhost:8080/api/usuarios/1`)
            .then((res) => {
                setUsuario(res.data);
                setFormData(res.data);
            })
            .catch((err) => console.error(err))
            .finally(() => setLoading(false));
    }, [userId]);

    // Manejar cambios de inputs
    const handleChange = (section, field, value, index = null) => {
        if (index !== null) {
            const newList = [...formData[section]];
            newList[index][field] = value;
            setFormData({ ...formData, [section]: newList });
        } else {
            setFormData({
                ...formData,
                [section]: { ...formData[section], [field]: value },
            });
        }
    };

    // Guardar cambios de una sección
    const handleSaveSection = (section) => {
        axios
            .put(`http://localhost:8080/api/usuarios/1`, formData)
            .then((res) => {
                setUsuario(res.data);
                setEditMode({ ...editMode, [section]: false });
                setMensaje(`${section} actualizado correctamente`);
            })
            .catch((err) => {
                console.error(err);
                setMensaje(`Error al actualizar ${section}`);
            });
    };

    if (loading) return <p>Cargando...</p>;
    if (!usuario) return <p>No se encontró el usuario</p>;

    return (
        <div style={{ maxWidth: "800px", margin: "20px auto", padding: "20px", border: "1px solid #ccc", borderRadius: "8px" }}>
            <h1>Mi Perfil</h1>

            {/* Pestañas */}
            <div style={{ marginBottom: "20px" }}>
                {tabs.map((tab) => (
                    <button
                        key={tab.key}
                        onClick={() => setActiveTab(tab.key)}
                        style={{
                            fontWeight: activeTab === tab.key ? "bold" : "normal",
                            marginRight: "5px",
                        }}
                    >
                        {tab.label}
                    </button>
                ))}
            </div>

            <div className="tab-content">
                {activeTab === "general" && (
                    <div>
                        {!editMode.general ? (
                            <div>
                                <h2>Datos Generales</h2>
                                {["rut", "nombre", "apellidos", "direccion", "sexo", "fechaNacimiento", "nacionalidad", "estadoCivil"].map((field) => (
                                    <p key={field}>
                                        <strong>{field.charAt(0).toUpperCase() + field.slice(1)}:</strong> {usuario[field] || "No especificado"}
                                    </p>
                                ))}
                                <p><strong>Discapacidad:</strong> {usuario.discapacidad ? "Sí" : "No"}</p>
                                <button onClick={() => setEditMode({ ...editMode, general: true })}>Editar</button>
                            </div>

                        ) : (
                            <div>
                                <h2>Editar Datos Generales</h2>
                                {["rut", "nombre", "apellidos", "direccion", "sexo", "fechaNacimiento", "nacionalidad", "estadoCivil"].map((field) => (
                                    <label key={field} style={{ display: "block", margin: "5px 0" }}>
                                        {field.charAt(0).toUpperCase() + field.slice(1)}:
                                        <input
                                            type={field === "fechaNacimiento" ? "date" : "text"}
                                            value={formData[field] || ""}
                                            onChange={(e) => setFormData({ ...formData, [field]: e.target.value })}
                                            style={{ marginLeft: "10px" }}
                                        />
                                    </label>
                                ))}
                                <label style={{ display: "block", margin: "5px 0" }}>
                                    Discapacidad:
                                    <input
                                        type="checkbox"
                                        checked={formData.general?.discapacidad || false}
                                        onChange={(e) => handleChange("general", "discapacidad", e.target.checked)}
                                        style={{ marginLeft: "10px" }}
                                    />
                                </label>
                                <button onClick={() => handleSaveSection("general")}>Guardar</button>
                                <button onClick={() => setEditMode({ ...editMode, general: false })} style={{ marginLeft: "10px" }}>Cancelar</button>
                            </div>
                        )}
                    </div>
                )}

                {/* CONDICIÓN LABORAL */}
                {activeTab === "condicion" && (
                    <div>
                        {!editMode.condicionLaboral ? (
                            <div>
                                <h2>Condición Laboral</h2>
                                <p><strong>Situación Laboral:</strong> {usuario.condicionLaboral?.situacionLaboral || "No especificado"}</p>
                                <p><strong>Fecha Inicio:</strong> {usuario.condicionLaboral?.fechaInicioSituacion || "No especificado"}</p>
                                <p><strong>Último Salario:</strong> {usuario.condicionLaboral?.ultimoSalarioLiquido || "No especificado"}</p>
                                <p><strong>Última Actividad:</strong> {usuario.condicionLaboral?.ultimaActividad || "No especificado"}</p>
                                <p><strong>Buscando Empleo:</strong> {usuario.condicionLaboral?.buscandoEmpleo ? "Sí" : "No"}</p>
                                <button onClick={() => {
                                    // Inicializamos formData con los datos actuales para editar
                                    setFormData((prev) => ({
                                        ...prev,
                                        condicionLaboral: { ...usuario.condicionLaboral }
                                    }));
                                    setEditMode({ ...editMode, condicionLaboral: true });
                                }}>Editar</button>
                            </div>
                        ) : (
                            <div>
                                <h2>Editar Condición Laboral</h2>
                                <label>
                                    Situación Laboral:
                                    <input
                                        type="text"
                                        value={formData.condicionLaboral?.situacionLaboral || ""}
                                        onChange={(e) => setFormData({
                                            ...formData,
                                            condicionLaboral: { ...formData.condicionLaboral, situacionLaboral: e.target.value }
                                        })}
                                    />
                                </label>
                                <label>
                                    Fecha Inicio:
                                    <input
                                        type="date"
                                        value={formData.condicionLaboral?.fechaInicioSituacion || ""}
                                        onChange={(e) => setFormData({
                                            ...formData,
                                            condicionLaboral: { ...formData.condicionLaboral, fechaInicioSituacion: e.target.value }
                                        })}
                                    />
                                </label>
                                <label>
                                    Último Salario:
                                    <input
                                        type="number"
                                        value={formData.condicionLaboral?.ultimoSalarioLiquido || 0}
                                        onChange={(e) => setFormData({
                                            ...formData,
                                            condicionLaboral: { ...formData.condicionLaboral, ultimoSalarioLiquido: e.target.value }
                                        })}
                                    />
                                </label>
                                <label>
                                    Última Actividad:
                                    <input
                                        type="text"
                                        value={formData.condicionLaboral?.ultimaActividad || ""}
                                        onChange={(e) => setFormData({
                                            ...formData,
                                            condicionLaboral: { ...formData.condicionLaboral, ultimaActividad: e.target.value }
                                        })}
                                    />
                                </label>
                                <label>
                                    Buscando Empleo:
                                    <input
                                        type="checkbox"
                                        checked={formData.condicionLaboral?.buscandoEmpleo || false}
                                        onChange={(e) => setFormData({
                                            ...formData,
                                            condicionLaboral: { ...formData.condicionLaboral, buscandoEmpleo: e.target.checked }
                                        })}
                                    />
                                </label>
                                <div style={{ marginTop: "10px" }}>
                                    <button onClick={() => {
                                        // Guardamos los cambios en el backend
                                        axios.put(`http://localhost:8080/api/usuarios/1`, {
                                            ...usuario,
                                            condicionLaboral: formData.condicionLaboral
                                        })
                                            .then((res) => {
                                                setUsuario(res.data); // Actualizamos el usuario con la respuesta del backend
                                                setEditMode({ ...editMode, condicionLaboral: false });
                                            })
                                            .catch((err) => console.error(err));
                                    }}>Guardar</button>
                                    <button onClick={() => setEditMode({ ...editMode, condicionLaboral: false })} style={{ marginLeft: "10px" }}>Cancelar</button>
                                </div>
                            </div>
                        )}
                    </div>
                )}


                {/* RESUMEN */}
                {activeTab === "resumen" && (
                    <div>
                        {!editMode.resumen ? (
                            <div>
                                <h2>Resumen</h2>
                                <p>{usuario.resumenPerfil?.descripcion || "No especificado"}</p>
                                <button onClick={() => setEditMode({ ...editMode, resumenPerfil: true })}>Editar</button>
                            </div>
                        ) : (
                            <div>
                                <h2>Editar Resumen</h2>
                                <textarea
                                    value={formData.resumenPerfil?.descripcion || ""}
                                    onChange={(e) => handleChange("resumenPerfil", "descripcion", e.target.value)}
                                    rows={5}
                                    cols={60}
                                />
                                <div style={{ marginTop: "10px" }}>
                                    <button onClick={() => handleSaveSection("resumenPerfil")}>Guardar</button>
                                    <button onClick={() => setEditMode({ ...editMode, resumenPerfil: false })} style={{ marginLeft: "10px" }}>Cancelar</button>
                                </div>
                            </div>
                        )}
                    </div>
                )}

                {/* Expectativa Laboral */}
                {activeTab === "expectativa" && (
                    <div>
                        {!editMode.expectativa ? (
                            <div>
                                <h2>Expectativa Laboral</h2>
                                {usuario.expectativa ? (
                                    <>
                                        <p><strong>Ocupación Deseada:</strong> {usuario.expectativa.ocupacionDeseada || "No especificado"}</p>
                                        <p><strong>Salario Deseado:</strong> {usuario.expectativa.salarioDeseado || "No especificado"}</p>
                                        <p><strong>Nivel Cargo:</strong> {usuario.expectativa.nivelCargo || "No especificado"}</p>
                                        <button onClick={() => setEditMode({ ...editMode, expectativa: true })}>Editar</button>
                                    </>
                                ) : <p>No hay información</p>}
                            </div>
                        ) : (
                            <div>
                                <h2>Editar Expectativa Laboral</h2>
                                <label>
                                    Ocupación Deseada:
                                    <input
                                        type="text"
                                        value={formData.expectativa?.ocupacionDeseada || ""}
                                        onChange={(e) => handleChange("expectativa", "ocupacionDeseada", e.target.value)}
                                    />
                                </label>
                                <label>
                                    Salario Deseado:
                                    <input
                                        type="number"
                                        value={formData.expectativa?.salarioDeseado || 0}
                                        onChange={(e) => handleChange("expectativa", "salarioDeseado", e.target.value)}
                                    />
                                </label>
                                <label>
                                    Nivel Cargo:
                                    <input
                                        type="text"
                                        value={formData.expectativa?.nivelCargo || ""}
                                        onChange={(e) => handleChange("expectativa", "nivelCargo", e.target.value)}
                                    />
                                </label>
                                <div style={{ marginTop: "10px" }}>
                                    <button onClick={() => handleSaveSection("expectativa")}>Guardar</button>
                                    <button onClick={() => setEditMode({ ...editMode, expectativa: false })} style={{ marginLeft: "10px" }}>Cancelar</button>
                                </div>
                            </div>
                        )}
                    </div>
                )}

                {/* Experiencias */}
                {activeTab === "experiencias" && (
                    <div>
                        {!editMode.experiencias ? (
                            <div>
                                <h2>Experiencias</h2>
                                {usuario.experiencias?.length > 0 ? usuario.experiencias.map((exp, i) => (
                                    <p key={i}><strong>{exp.ocupacion}</strong> en {exp.empresa}</p>
                                )) : <p>No hay información</p>}
                                <button onClick={() => setEditMode({ ...editMode, experiencias: true })}>Editar</button>
                            </div>
                        ) : (
                            <div>
                                <h2>Editar Experiencias</h2>
                                {formData.experiencias?.map((exp, i) => (
                                    <div key={i} style={{ marginBottom: "10px", border: "1px solid #ccc", padding: "5px" }}>
                                        <label>
                                            Ocupación:
                                            <input
                                                type="text"
                                                value={exp.ocupacion || ""}
                                                onChange={(e) => handleChange("experiencias", "ocupacion", e.target.value, i)}
                                            />
                                        </label>
                                        <label>
                                            Empresa:
                                            <input
                                                type="text"
                                                value={exp.empresa || ""}
                                                onChange={(e) => handleChange("experiencias", "empresa", e.target.value, i)}
                                            />
                                        </label>
                                    </div>
                                ))}
                                <div style={{ marginTop: "10px" }}>
                                    <button onClick={() => handleSaveSection("experiencias")}>Guardar</button>
                                    <button onClick={() => setEditMode({ ...editMode, experiencias: false })} style={{ marginLeft: "10px" }}>Cancelar</button>
                                </div>
                            </div>
                        )}
                    </div>
                )}

                {/* Las demás pestañas: referencias, educación, capacitaciones, idiomas */}
                {["referencias", "educacion", "capacitaciones", "idiomas"].includes(activeTab) && (
                    <div>
                        {!editMode[activeTab] ? (
                            <div>
                                <h2>{tabs.find(t => t.key === activeTab)?.label}</h2>
                                {usuario[activeTab]?.length > 0 ? usuario[activeTab].map((item, i) => (
                                    <p key={i}>{JSON.stringify(item)}</p>
                                )) : <p>No hay información</p>}
                                <button onClick={() => setEditMode({ ...editMode, [activeTab]: true })}>Editar</button>
                            </div>
                        ) : (
                            <div>
                                <h2>Editar {tabs.find(t => t.key === activeTab)?.label}</h2>
                                {formData[activeTab]?.map((item, i) => (
                                    <div key={i} style={{ marginBottom: "10px", border: "1px solid #ccc", padding: "5px" }}>
                                        {Object.keys(item).map((field) => (
                                            <label key={field} style={{ display: "block", margin: "5px 0" }}>
                                                {field.charAt(0).toUpperCase() + field.slice(1)}:
                                                <input
                                                    type="text"
                                                    value={item[field] || ""}
                                                    onChange={(e) => handleChange(activeTab, field, e.target.value, i)}
                                                />
                                            </label>
                                        ))}
                                    </div>
                                ))}
                                <div style={{ marginTop: "10px" }}>
                                    <button onClick={() => handleSaveSection(activeTab)}>Guardar</button>
                                    <button onClick={() => setEditMode({ ...editMode, [activeTab]: false })} style={{ marginLeft: "10px" }}>Cancelar</button>
                                </div>
                            </div>
                        )}
                    </div>
                )}
            </div>

            {mensaje && <p style={{ marginTop: "10px", color: "green" }}>{mensaje}</p>}
        </div>
    );
}