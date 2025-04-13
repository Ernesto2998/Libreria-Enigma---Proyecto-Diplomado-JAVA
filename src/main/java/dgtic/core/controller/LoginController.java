package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("valor", "Bienvenido");
        model.addAttribute("mensaje", "Inicia sesión");
        model.addAttribute("contenido", "Inicio de Sesión");
        model.addAttribute("contenido2", "Por favor ingresa tus datos para continuar.");
        return "login";
    }

//    @GetMapping("/principal")
//    public String mostrarPrincipal() {
//        return "principal";
//    }
}