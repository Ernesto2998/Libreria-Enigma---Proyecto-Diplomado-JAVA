package dgtic.core.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("error", "El número de empleado debe ser un número entero.");
        model.addAttribute("contenido", "En esta página habrá un login.");
        model.addAttribute("contenido2", "Esta página cambiará en 10 seg");

        return "login"; // Regresa a la vista de login con el mensaje de error
    }
}
