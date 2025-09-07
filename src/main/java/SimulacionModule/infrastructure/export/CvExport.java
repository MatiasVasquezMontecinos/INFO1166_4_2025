package SimulacionModule.infrastructure.export;

import SimulacionModule.entity.Usuario;

public interface CvExport {
    void exportarCv(Usuario usuario, String path) throws Exception;
}
