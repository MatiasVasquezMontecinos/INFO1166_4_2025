package SimulacionModule.domain.perfil;

import java.util.Objects;
import java.util.UUID;

public final class PerfilId {
    private final String value;

    private PerfilId(String value) {
        this.value = value;
    }

    public static PerfilId of(String value) {
        Objects.requireNonNull(value, "PerfilId no puede ser null");
        return new PerfilId(value);
    }

    public static PerfilId newId() {
        return new PerfilId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerfilId)) return false;
        PerfilId perfilId = (PerfilId) o;
        return value.equals(perfilId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
