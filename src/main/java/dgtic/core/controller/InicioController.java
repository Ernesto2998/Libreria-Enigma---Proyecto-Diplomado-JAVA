package dgtic.core.controller;

import dgtic.core.security.JwtService;
import dgtic.core.service.autentificacion.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


@Controller
public class InicioController {

    @Value("${mensaje.application}")
    private String valor;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("valor", valor);
        model.addAttribute("contenido", "En esta página habrá un login.");
        model.addAttribute("contenido2", "Esta página cambiará en 10 seg");
        return "login";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("valor", valor);
        model.addAttribute("contenido", "En esta página habrá un login.");
        model.addAttribute("contenido2", "Esta página cambiará en 10 seg");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam Integer numEmpleado,
                        @RequestParam String contrasenia,
                        HttpServletResponse response,
                        Model model) {

        if (authService.authenticate(numEmpleado, contrasenia)) {
            String nivelAcceso = authService.getNivelAcceso(numEmpleado);
            String token = jwtService.generateToken(numEmpleado, nivelAcceso);

            // Crear cookie JWT
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            // Crear autoridades (roles)
            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + nivelAcceso.toUpperCase())
            );

            // Establecer contexto de seguridad manualmente
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    numEmpleado.toString(), null, authorities
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "redirect:/principal";
        }

        model.addAttribute("valor", valor);
        model.addAttribute("contenido", "En esta página habrá un login.");
        model.addAttribute("contenido2", "Esta página cambiará en 10 seg");
        model.addAttribute("error", "Credenciales inválidas");
        return "login";
    }

    @GetMapping("/principal")
    public String principal(HttpServletRequest request, Model model) {
        String token = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null || !jwtService.validateToken(token)) {
            System.out.println("Token no válido o no existe");
            return "redirect:/";
        }

        Integer numEmpleado = jwtService.extractNumEmpleado(token);
        model.addAttribute("numEmpleado", numEmpleado);
        return "principal";
    }

//    @GetMapping("/logout")
//    public String logout(HttpServletResponse response) {
//        Cookie cookie = new Cookie("jwt", "");
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        return "redirect:/";
//    }
}



