package SimulacionModule.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LicenciasConducir")
@Data
public class LicenciaConducir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoLicencia;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
