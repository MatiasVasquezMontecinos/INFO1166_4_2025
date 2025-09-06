package SimulacionModule.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Vehiculos")
@Data
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoVehiculo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
