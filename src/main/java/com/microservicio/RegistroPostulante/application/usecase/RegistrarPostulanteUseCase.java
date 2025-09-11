package com.microservicio.RegistroPostulante.application.usecase;

import com.microservicio.RegistroPostulante.domain.model.Postulante;
import com.microservicio.RegistroPostulante.domain.repository.PostulanteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrarPostulanteUseCase {
    private final PostulanteRepository postulanteRepository;

    public void registrar(Postulante postulante) {

        if (postulante.getRut() == null || postulante.getRut().isBlank()) {
            throw new IllegalArgumentException("El RUT del postulante es obligatorio.");
        }
        log.info("Simulando obtención de datos de postulante (Clave Única).");
        postulanteRepository.save(postulante);
    }
}
