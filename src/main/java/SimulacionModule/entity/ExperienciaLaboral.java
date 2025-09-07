package SimulacionModule.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ExperienciaLaboral")
@Data
public class ExperienciaLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ocupacion;
    private String nivelCargo;
    private String empresa;
    private String region;
    private Integer experienciaMeses;
    private String cartaReferencia;
    private String fechaInicio;
    private String fechaFin;
    private Double sueldoBruto;

    @Column(length = 1000)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
