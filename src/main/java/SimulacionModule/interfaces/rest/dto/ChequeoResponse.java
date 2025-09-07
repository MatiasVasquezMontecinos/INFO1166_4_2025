package SimulacionModule.interfaces.rest.dto;

import SimulacionModule.domain.perfil.EstadoPerfil;
import java.util.List;

public class ChequeoResponse {
    public boolean existe;
    public EstadoPerfil estado;
    public List<String> motivos;

    public ChequeoResponse(boolean existe, EstadoPerfil estado, List<String> motivos) {
        this.existe = existe;
        this.estado = estado;
        this.motivos = motivos;
    }
}
