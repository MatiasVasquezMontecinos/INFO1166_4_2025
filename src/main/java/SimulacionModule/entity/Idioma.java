package SimulacionModule.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Idiomas")
@Data
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idioma;
    private String nivelLectura;
    private String nivelEscritura;
    private String nivelHabla;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
