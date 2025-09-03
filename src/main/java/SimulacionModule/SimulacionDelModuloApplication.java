package SimulacionModule; // o el que tengas

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "controller", "domain", "infrastructure" })
public class SimulacionDelModuloApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimulacionDelModuloApplication.class, args);
    }
}
