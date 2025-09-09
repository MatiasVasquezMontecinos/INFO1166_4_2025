package SimulacionModule.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private String rut;

    private String password;

    private String nombre;

    // relaciones
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private CondicionLaboralActual condicionLaboral;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ResumenPerfil resumenPerfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ExperienciaLaboral> experiencias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ReferenciaLaboral> referencias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InformacionAcademica> educacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Capacitacion> capacitaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Idioma> idiomas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LicenciaConducir> licencias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vehiculo> vehiculos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Competencia> competencias;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ExpectativaLaboral expectativa;

    @PrePersist
    @PreUpdate
    private void fixRelaciones() {
        if (condicionLaboral != null) condicionLaboral.setUsuario(this);
        if (resumenPerfil != null) resumenPerfil.setUsuario(this);
        if (expectativa != null) expectativa.setUsuario(this);

        if (experiencias != null) experiencias.forEach(e -> e.setUsuario(this));
        if (referencias != null) referencias.forEach(r -> r.setUsuario(this));
        if (educacion != null) educacion.forEach(e -> e.setUsuario(this));
        if (capacitaciones != null) capacitaciones.forEach(c -> c.setUsuario(this));
        if (idiomas != null) idiomas.forEach(i -> i.setUsuario(this));
        if (licencias != null) licencias.forEach(l -> l.setUsuario(this));
        if (vehiculos != null) vehiculos.forEach(v -> v.setUsuario(this));
        if (competencias != null) competencias.forEach(c -> c.setUsuario(this));
    }
}