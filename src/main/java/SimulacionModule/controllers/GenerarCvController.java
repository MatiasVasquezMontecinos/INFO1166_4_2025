package SimulacionModule.controllers;

import SimulacionModule.application.perfil.CvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cv")
public class GenerarCvController {

    private final CvService cvService;

    public GenerarCvController(CvService service) {
        this.cvService = service;
    }

    @GetMapping("/{tipo}/{id}")
    public ResponseEntity<String> generarCv(
            @PathVariable String tipo,
            @PathVariable Long id // Cambiado a Long
    ) {
        try {
            String path;
            if (tipo.equalsIgnoreCase("pdf")) {
                path = "cv_usuario_"  + id + ".pdf";
            } else if (tipo.equalsIgnoreCase("word")) {
                path = "cv_usuario_" + id + ".docx";
            } else {
                return ResponseEntity.badRequest().body("Tipo no válido. Usa 'pdf' o 'word'.");
            }

            cvService.generCV(id, tipo, path);
            return ResponseEntity.ok("CV generado con éxito: " + path);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
