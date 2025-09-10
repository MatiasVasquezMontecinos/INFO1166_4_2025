package SimulacionModule.controllers;

import SimulacionModule.entity.Usuario;
import SimulacionModule.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")  // api vite
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET - Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // GET - Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - Obtener usuario por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Usuario> getUsuarioByNombre(@PathVariable String nombre) {
        Optional<Usuario> usuario = usuarioRepository.findByNombre(nombre);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // GET - Obtener siempre el usuario con ID = 1
    @GetMapping("/default")
    public ResponseEntity<Usuario> getUsuarioDefault() {
        Optional<Usuario> usuario = usuarioRepository.findById(1L);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        try {
            // Verificar si ya existe un usuario con ese nombre
            if (usuarioRepository.existsByNombre(usuario.getNombre())) {
                return ResponseEntity.badRequest()
                        .body(null); // O podrías devolver un mensaje de error
            }

            Usuario nuevoUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT - Actualizar usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            // Actualizar todos los campos
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellidos(usuarioDetails.getApellidos());
            usuario.setDireccion(usuarioDetails.getDireccion());
            usuario.setSexo(usuarioDetails.getSexo());
            usuario.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
            usuario.setNacionalidad(usuarioDetails.getNacionalidad());
            usuario.setEstadoCivil(usuarioDetails.getEstadoCivil());
            usuario.setDiscapacidad(usuarioDetails.getDiscapacidad());
            usuario.setPassword(usuarioDetails.getPassword());
            usuario.setRut(usuarioDetails.getRut());
            usuario.getCondicionLaboral().setSituacionLaboral(usuarioDetails.getCondicionLaboral().getSituacionLaboral());
            usuario.getCondicionLaboral().setFechaInicioSituacion(usuarioDetails.getCondicionLaboral().getFechaInicioSituacion());
            usuario.getCondicionLaboral().setUltimoSalarioLiquido(usuarioDetails.getCondicionLaboral().getUltimoSalarioLiquido());
            usuario.getCondicionLaboral().setUltimaActividad(usuarioDetails.getCondicionLaboral().getUltimaActividad());
            usuario.getCondicionLaboral().setBuscandoEmpleo(usuarioDetails.getCondicionLaboral().getBuscandoEmpleo());
            usuario.setResumenPerfil(usuarioDetails.getResumenPerfil());
            usuario.setExpectativa(usuarioDetails.getExpectativa());
            usuario.setDirecciones(usuarioDetails.getDirecciones());
            usuario.setContactos(usuarioDetails.getContactos());
            usuario.setExperiencias(usuarioDetails.getExperiencias());
            usuario.setReferencias(usuarioDetails.getReferencias());
            usuario.setEducacion(usuarioDetails.getEducacion());
            usuario.setCapacitaciones(usuarioDetails.getCapacitaciones());
            usuario.setIdiomas(usuarioDetails.getIdiomas());



            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // DELETE - Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}