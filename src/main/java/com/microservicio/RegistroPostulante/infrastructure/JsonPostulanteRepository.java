package com.microservicio.RegistroPostulante.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.RegistroPostulante.domain.model.Postulante;
import com.microservicio.RegistroPostulante.domain.repository.PostulanteRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Component
public class JsonPostulanteRepository implements PostulanteRepository {

    private static final String JSON_FILE_PATH = "postulantes.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void save(Postulante postulante) {
        List<Postulante> postulantes = new ArrayList<>(findAll());

        // Evitar duplicados por RUT (básico)
        boolean exists = postulantes.stream()
                .anyMatch(p -> p.getRut() != null && p.getRut().equalsIgnoreCase(postulante.getRut()));
        if (exists) {
            throw new IllegalArgumentException("Ya existe un postulante con el RUT indicado.");
        }

        postulantes.add(postulante);
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(JSON_FILE_PATH), postulantes);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar postulante en JSON", e);
        }
    }

    @Override
    public Optional<Postulante> findByRut(String rut) {
        if (rut == null) return Optional.empty();
        return findAll().stream()
                .filter(p -> p.getRut() != null && p.getRut().equalsIgnoreCase(rut))
                .findFirst();
    }

    @Override
    public List<Postulante> findAll() {
        try {
            File file = new File(JSON_FILE_PATH);
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Postulante>>() {});
        } catch (IOException e) {
            // Si el archivo está corrupto o vacío, devuelvo lista vacía para no romper el flujo
            return new ArrayList<>();
        }
    }
}

