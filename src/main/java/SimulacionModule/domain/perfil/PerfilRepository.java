package SimulacionModule.domain.perfil;

import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) del dominio para acceder a Perfiles
 */
public interface PerfilRepository {
    Optional<Perfil> buscarPorId(PerfilId id);
    Optional<Perfil> buscarPorEmail(String email);
    List<Perfil> listar();
}
