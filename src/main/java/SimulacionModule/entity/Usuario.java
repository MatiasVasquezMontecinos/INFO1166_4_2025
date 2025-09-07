package SimulacionModule.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table (name = "Usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;  // opcional si necesitas datos básicos

    // relaciones
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private CondicionLaboralActual condicionLaboral;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ResumenPerfil resumenPerfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ExperienciaLaboral> experiencias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ReferenciaLaboral> referencias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<InformacionAcademica> educacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Capacitacion> capacitaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Idioma> idiomas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<LicenciaConducir> licencias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Competencia> competencias;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ExpectativaLaboral expectativa;
}
