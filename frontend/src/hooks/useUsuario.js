import { useState } from "react";
import * as usuarioService from "../application/usuarioService";

export function useUsuario() {
    const [usuario, setUsuario] = useState(null);

    async function registrarUsuario(nombre, password) {
        const u = await usuarioService.registrar(nombre, password);
        setUsuario(u);
        return u;
    }

    async function loginUsuario(nombre, password) {
        const u = await usuarioService.login(nombre, password);
        setUsuario(u);
        return u;
    }

    return { usuario, registrarUsuario, loginUsuario };
}
