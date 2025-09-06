package SimulacionModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
		"entity",
		"SimulacionModule.entity",          // Entidades en SimulacionModule.entity
		"SimulacionModule.domain.entity"    // Entidades en SimulacionModule.domain.entity (por si acaso)
})
@EnableJpaRepositories(basePackages = {
		"repository",
		"SimulacionModule.repository",      // Repositorios en SimulacionModule.repository
		"SimulacionModule.infrastructure.repository"  // ← ¡ESTA ES LA QUE FALTA!
})
public class SimulacionDelModuloApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimulacionDelModuloApplication.class, args);
	}
}