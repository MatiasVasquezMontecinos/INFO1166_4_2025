package SimulacionModule.interfaces.rest;

import SimulacionModule.application.perfil.ChequearPerfilService;
import SimulacionModule.application.perfil.ResultadoChequeo;
import SimulacionModule.domain.perfil.Perfil;
import SimulacionModule.domain.perfil.PerfilRepository;
import SimulacionModule.interfaces.rest.dto.ChequeoRequest;
import SimulacionModule.interfaces.rest.dto.ChequeoResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {

    private final ChequearPerfilService chequearPerfilService;
    private final PerfilRepository perfilRepository;

    public PerfilController(ChequearPerfilService chequearPerfilService, PerfilRepository perfilRepository) {
        this.chequearPerfilService = chequearPerfilService;
        this.perfilRepository = perfilRepository;
    }

    @GetMapping
    public List<Perfil> listar() {
        return perfilRepository.listar();
    }

    @PostMapping("/chequear")
    public ResponseEntity<ChequeoResponse> chequear(@RequestBody ChequeoRequest req) {
        ResultadoChequeo res;
        if (req.email != null && !req.email.isBlank()) {
            res = chequearPerfilService.chequearPorEmail(req.email);
        } else if (req.id != null && !req.id.isBlank()) {
            res = chequearPerfilService.chequearPorId(req.id);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new ChequeoResponse(res.existe, res.estado, res.motivos));
    }
}
