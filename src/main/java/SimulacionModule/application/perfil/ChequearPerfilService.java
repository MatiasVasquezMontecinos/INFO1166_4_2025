package SimulacionModule.application.perfil;

import SimulacionModule.domain.perfil.Perfil;
import SimulacionModule.domain.perfil.PerfilId;
import SimulacionModule.domain.perfil.PerfilRepository;
//import SimulacionModule.domain.perfil.EstadoPerfil;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Caso de uso (aplicación): Chequear Perfil
 * Patrón: Service + Repository
 */
@Service
public class ChequearPerfilService {

    private final PerfilRepository repository;

    public ChequearPerfilService(PerfilRepository repository) {
        this.repository = repository;
    }

    public ResultadoChequeo chequearPorEmail(String email) {
        Optional<Perfil> perfilOpt = repository.buscarPorEmail(email);
        return evaluar(perfilOpt);
    }

    public ResultadoChequeo chequearPorId(String id) {
        Optional<Perfil> perfilOpt = repository.buscarPorId(PerfilId.of(id));
        return evaluar(perfilOpt);
    }

    private ResultadoChequeo evaluar(Optional<Perfil> perfilOpt) {
        List<String> motivos = new ArrayList<>();
        if (perfilOpt.isEmpty()) {
            motivos.add("No existe un perfil asociado.");
            return new ResultadoChequeo(false, null, motivos);
        }
        Perfil perfil = perfilOpt.get();
        if (!perfil.estaActivo()) {
            motivos.add("El perfil no está activo: " + perfil.getEstado());
        }
        if (perfil.getRoles().isEmpty()) {
            motivos.add("El perfil no tiene roles asignados.");
        }
        boolean ok = perfil.estaActivo() && !perfil.getRoles().isEmpty();
        return new ResultadoChequeo(true, perfil.getEstado(), motivos);
    }
}
