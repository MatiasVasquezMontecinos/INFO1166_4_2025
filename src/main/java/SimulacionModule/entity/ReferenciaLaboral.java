package SimulacionModule.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ReferenciasLaborales")
@Data
public class ReferenciaLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    private String puesto;
    private String empresa;
    private String telefono;
    private String email;
    private Boolean sinReferencias = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
