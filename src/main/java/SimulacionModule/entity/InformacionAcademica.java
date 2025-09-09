package SimulacionModule.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "InformacionAcademica")
@Data
public class InformacionAcademica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nivelEducacional;
    private String area;
    private String titulo;
    private String fechaFinalizacion;
    private String institucion;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;
}
