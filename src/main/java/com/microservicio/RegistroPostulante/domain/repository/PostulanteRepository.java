package com.microservicio.RegistroPostulante.domain.repository;

import com.microservicio.RegistroPostulante.domain.model.Postulante;
import java.util.Optional;
import java.util.List;

public interface PostulanteRepository {
    void save(Postulante postulante);
    Optional<Postulante> findByRut(String rut);
    List<Postulante> findAll();
}
