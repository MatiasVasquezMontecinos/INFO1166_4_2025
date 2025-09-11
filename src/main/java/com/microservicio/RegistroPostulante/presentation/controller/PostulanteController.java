package com.microservicio.RegistroPostulante.presentation.controller;

import com.microservicio.RegistroPostulante.application.usecase.BuscarPostulanteUseCase;
import com.microservicio.RegistroPostulante.application.usecase.RegistrarPostulanteUseCase;
import com.microservicio.RegistroPostulante.domain.model.Postulante;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/postulantes")
@RequiredArgsConstructor
public class PostulanteController {

    private final RegistrarPostulanteUseCase registrarPostulanteUseCase;
    private final BuscarPostulanteUseCase buscarPostulanteUseCase;

    @PostMapping
    public ResponseEntity<?> registrarPostulante(@RequestBody Postulante postulante) {
        try {
            registrarPostulanteUseCase.registrar(postulante);
            return ResponseEntity.ok("Postulante registrado con éxito.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{rut}")
    public ResponseEntity<?> buscarPostulanteUsecase(@PathVariable String rut) {
        return buscarPostulanteUseCase.buscarPorRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}