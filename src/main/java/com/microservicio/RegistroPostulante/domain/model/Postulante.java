package com.microservicio.RegistroPostulante.domain.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Postulante {
    private String nombre;
    private String rut;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String dondeTrabajo;
    private String dondeEstudio;
}



