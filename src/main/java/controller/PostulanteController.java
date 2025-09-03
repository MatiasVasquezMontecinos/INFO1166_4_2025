package controller;

import domain.model.DatosPostulante;
import domain.model.Postulante;
import domain.repository.PostulanteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {

    private final PostulanteRepository repository;

    public PostulanteController(PostulanteRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody DatosPostulante postulante) {
        if (postulante.getRut() == null || postulante.getRut().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El RUT es obligatorio.");
        }
        String rutNormalizado = normalizeRut(postulante.getRut());
        postulante.setRut(rutNormalizado);

        try {
            boolean existe = repository.buscarPorRut(rutNormalizado).isPresent();
            repository.guardar(postulante);
            return ResponseEntity.status(existe ? HttpStatus.OK : HttpStatus.CREATED)
                    .body(existe ? "Postulante actualizado." : "Postulante registrado.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el postulante.");
        }
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Postulante> obtener(@PathVariable String rut) {
        String rutNormalizado = normalizeRut(rut);
        Optional<Postulante> postulante = repository.buscarPorRut(rutNormalizado);
        return postulante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private static String normalizeRut(String rut) {
        if (rut == null) return null;
        String r = rut.replace(".", "").replaceAll("\\s+", "").toUpperCase();
        if (!r.contains("-") && r.length() > 1) {
            r = r.substring(0, r.length() - 1) + "-" + r.substring(r.length() - 1);
        }
        return r;
    }
}
