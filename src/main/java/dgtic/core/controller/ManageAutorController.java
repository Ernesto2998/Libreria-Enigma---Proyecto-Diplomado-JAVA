package dgtic.core.controller;

import dgtic.core.model.Autor;
import dgtic.core.service.autor.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping(value = "user/manager/gestionar")
public class ManageAutorController {

    @Autowired
    MessageSource mensaje;

    @Autowired
    AutorService autorService;

    @GetMapping("autor")
    public String getGestionar(Model modelo) {

        modelo.addAttribute("autor", new Autor());
        modelo.addAttribute("contenido", "Gestionar Autor");
        modelo.addAttribute("description",
                "Se mostrará una tabla con Autores y se podrá editar o añadir");
        return "principal/gestionAutor";
    }

    @PostMapping("recibir-autor")
    public String recibirAutor(@Valid Autor autor,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/gestionAutor";
        }

        try {
            autorService.save(autor);
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.autorDuplicado",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("nombre", "nombre", msg);
            bindingResult.rejectValue("apellidoUno", "apellidoUno", msg);
            bindingResult.rejectValue("apellidoDos", "apellidoDos", msg);
            model.addAttribute("showModal", true); // También abre el modal si hay error de BD
            return "principal/gestionAutor";
        }

        String cadena = "Autor : " + autor.getNombre() + " " + autor.getApellidoUno() + " " + autor.getApellidoDos() +
                "\nNacionalidad: " + autor.getNacionalidad();
        model.addAttribute("info", cadena);
        model.addAttribute("autor", new Autor());
        model.addAttribute("contenido", "Los datos que ingresas son:");
        model.addAttribute("description", cadena);

        return "principal/gestionAutor";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String errorRuntimeDuplicated(SQLIntegrityConstraintViolationException e,
                                         Model model) {
        String msg = mensaje.getMessage("Error.base.autorDuplicado",
                null, LocaleContextHolder.getLocale());
        model.addAttribute("explicacion", msg);
        return "error-general";
    }
}
