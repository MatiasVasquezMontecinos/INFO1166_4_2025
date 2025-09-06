package SimulacionModule.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerarCvRestController {

    @GetMapping("/hello")
    public String Helloworld(){
        return "Helloworld";
    }
}
