package SimulacionModule.infrastructure.repository;

import SimulacionModule.domain.perfil.*;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Adaptador de infraestructura (en memoria) para el puerto PerfilRepository
 * Registros simulados.
 */
@Repository
public class InMemoryPerfilRepository implements PerfilRepository {

    private final Map<String, Perfil> data = new ConcurrentHashMap<>();

    @PostConstruct
    public void seed() {
        Perfil p1 = new Perfil(PerfilId.newId(), "Ana Pérez", "ana@example.com", EstadoPerfil.ACTIVO, EnumSet.of(Rol.USUARIO));
        Perfil p2 = new Perfil(PerfilId.newId(), "Carlos Soto", "carlos@example.com", EstadoPerfil.SUSPENDIDO, EnumSet.of(Rol.USUARIO));
        Perfil p3 = new Perfil(PerfilId.newId(), "Admin Root", "admin@example.com", EstadoPerfil.ACTIVO, EnumSet.of(Rol.ADMIN, Rol.USUARIO));
        Perfil p4 = new Perfil(PerfilId.newId(), "Visitante", "visit@example.com", EstadoPerfil.INACTIVO, EnumSet.noneOf(Rol.class));

        for (Perfil p : List.of(p1, p2, p3, p4)) {
            data.put(p.getId().getValue(), p);
        }
    }

    @Override
    public Optional<Perfil> buscarPorId(PerfilId id) {
        return Optional.ofNullable(data.get(id.getValue()));
    }

    @Override
    public Optional<Perfil> buscarPorEmail(String email) {
        return data.values().stream().filter(p -> p.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    @Override
    public List<Perfil> listar() {
        return new ArrayList<>(data.values());
    }
}
