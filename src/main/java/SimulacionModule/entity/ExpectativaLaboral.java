package SimulacionModule.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ExpectaticasLaborales")
@Data
public class ExpectativaLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ocupacionDeseada;
    private Double salarioDeseado;
    private String nivelCargo;
    private Integer experienciaMeses;
    private String jornada;
    private String regionComuna;
    private String tipoContrato;
    private String turnos; // puede ser lista de valores

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
