const API_URL = "http://localhost:8080/api/usuarios";

export async function registrarUsuario(usuario) {
    const res = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(usuario)
    });
    return res.json();
}

export async function loginUsuario(usuario) {
    const res = await fetch(`${API_URL}/login`, { // si implementas endpoint login
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(usuario)
    });

    if (!res.ok) return null;
    return res.json();
}
