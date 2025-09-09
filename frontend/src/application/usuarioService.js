import { Usuario } from "../domain/Usuario";
import { registrarUsuario, loginUsuario } from "../api/usuarioApi";

export async function registrar(nombre, password) {
    const usuario = new Usuario(null, nombre, password);
    return await registrarUsuario(usuario);
}

export async function login(nombre, password) {
    return await loginUsuario({ nombre, password });
}
