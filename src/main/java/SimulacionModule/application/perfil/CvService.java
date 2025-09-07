package SimulacionModule.application.perfil;

import SimulacionModule.entity.Usuario;
import SimulacionModule.infrastructure.repository.UsuarioRepository;
import SimulacionModule.infrastructure.export.CvExport;
import SimulacionModule.infrastructure.export.CvExportFactory   ;
import org.springframework.stereotype.Service;

@Service
public class CvService {
    private final UsuarioRepository usuarioRepository;

    public CvService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void generCV(Long usuarioId, String tipo, String path) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new Exception("Usuario no encontrado"));

        CvExport exporter = CvExportFactory.exportarCv(tipo);
        exporter.exportarCv(usuario, path);
    }
}
