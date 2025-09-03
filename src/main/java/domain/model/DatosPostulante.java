package domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatosPostulante implements Postulante {
    private String rut;
    private String nombre;
    private String correo;
    private String telefono;
    private DatosCurriculares datosCurriculares;

    @Override public String getRut() { return rut; }
    @Override public String getNombre() { return nombre; }
    @Override public String getCorreo() { return correo; }
    @Override public String getTelefono() { return telefono; }
    @Override public DatosCurriculares getDatosCurriculares() { return datosCurriculares; }

    public void setRut(String rut) { this.rut = rut; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setDatosCurriculares(DatosCurriculares datosCurriculares) {
        this.datosCurriculares = datosCurriculares;
    }
}
