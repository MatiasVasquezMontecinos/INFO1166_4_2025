package SimulacionModule.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ResumenesPerfiles")
@Data
public class ResumenPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
