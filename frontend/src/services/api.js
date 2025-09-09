import axios from "axios";

const API_URL = "http://localhost:8080/api/usuarios";

export const getUsuarios = () => axios.get(API_URL);
export const createUsuario = (usuario) => axios.post(API_URL, usuario);
