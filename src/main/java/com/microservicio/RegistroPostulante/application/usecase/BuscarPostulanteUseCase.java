package com.microservicio.RegistroPostulante.application.usecase;

import com.microservicio.RegistroPostulante.domain.model.Postulante;
import com.microservicio.RegistroPostulante.domain.repository.PostulanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuscarPostulanteUseCase {
    private final PostulanteRepository postulanteRepository;

    public Optional<Postulante> buscarPorRut(String rut) {
        if (rut == null || rut.isBlank()) {
            return Optional.empty();
        }
        return postulanteRepository.findByRut(rut);
    }
}
