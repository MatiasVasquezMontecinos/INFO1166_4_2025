package SimulacionModule.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table (name = "CondicionesLaborales")
@Data
public class CondicionLaboralActual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String situacionLaboral; // lista de opciones
    private String fechaInicioSituacion;
    private Double ultimoSalarioLiquido;
    private String ultimaActividad;
    private Boolean buscandoEmpleo = false;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}



