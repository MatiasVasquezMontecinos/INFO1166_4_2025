package SimulacionModule.application.perfil;

import SimulacionModule.domain.perfil.EstadoPerfil;
import java.util.List;

/**
 * DTO de salida del caso de uso Chequear Perfil
 */
public class ResultadoChequeo {
    public final boolean existe;
    public final EstadoPerfil estado;
    public final List<String> motivos;

    public ResultadoChequeo(boolean existe, EstadoPerfil estado, List<String> motivos) {
        this.existe = existe;
        this.estado = estado;
        this.motivos = motivos;
    }
}
