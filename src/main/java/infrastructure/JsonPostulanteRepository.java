package infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.DatosPostulante;
import domain.model.Postulante;
import domain.repository.PostulanteRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class JsonPostulanteRepository implements PostulanteRepository {

    private static final String ARCHIVO_JSON = "postulantes.json";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, Postulante> postulantes = new HashMap<>();

    @PostConstruct
    public synchronized void init() {
        File file = new File(ARCHIVO_JSON);
        if (!file.exists()) {
            try {
                guardarEnArchivo(); // crea {} inicial
            } catch (IOException e) {
                throw new RuntimeException("No se pudo inicializar el archivo JSON", e);
            }
            return;
        }
        try {
            Map<String, DatosPostulante> leidos =
                    objectMapper.readValue(file, new TypeReference<Map<String, DatosPostulante>>() {});
            postulantes.clear();
            postulantes.putAll(leidos); // DatosPostulante implementa Postulante
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo " + ARCHIVO_JSON, e);
        }
    }

    @Override
    public synchronized void guardar(Postulante postulante) throws IOException {
        postulantes.put(postulante.getRut(), postulante);
        guardarEnArchivo();
    }

    @Override
    public synchronized Optional<Postulante> buscarPorRut(String rut) {
        return Optional.ofNullable(postulantes.get(rut));
    }

    private void guardarEnArchivo() throws IOException {
        File tmp = new File(ARCHIVO_JSON + ".tmp");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(tmp, postulantes);
        File dest = new File(ARCHIVO_JSON);
        if (!tmp.renameTo(dest)) {
            // fallback si el rename falla en el SO
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(dest, postulantes);
            tmp.delete();
        }
    }
}
