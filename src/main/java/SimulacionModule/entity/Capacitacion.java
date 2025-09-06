package SimulacionModule.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Capacitaciones")
@Data
public class Capacitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCurso;
    private Boolean visibleEmpleadores;
    private String institucion;
    private Integer duracionHoras;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
