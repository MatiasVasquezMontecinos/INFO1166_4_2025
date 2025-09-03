package domain.repository;

import domain.model.Postulante;
import java.io.IOException;
import java.util.Optional;

public interface PostulanteRepository {
    void guardar(Postulante postulante) throws IOException;
    Optional<Postulante> buscarPorRut(String rut);
}
