package SimulacionModule.domain.perfil;

import java.util.Objects;
import java.util.Set;

public class Perfil {
    private final PerfilId id;
    private final String nombre;
    private final String email;
    private EstadoPerfil estado;
    private final Set<Rol> roles;

    public Perfil(PerfilId id, String nombre, String email, EstadoPerfil estado, Set<Rol> roles) {
        this.id = Objects.requireNonNull(id);
        this.nombre = Objects.requireNonNull(nombre);
        this.email = Objects.requireNonNull(email);
        this.estado = Objects.requireNonNull(estado);
        this.roles = Objects.requireNonNull(roles);
    }

    public PerfilId getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public EstadoPerfil getEstado() { return estado; }
    public Set<Rol> getRoles() { return roles; }

    public boolean estaActivo() { return this.estado == EstadoPerfil.ACTIVO; }

    public void suspender() { this.estado = EstadoPerfil.SUSPENDIDO; }

    public void activar() { this.estado = EstadoPerfil.ACTIVO; }
}
