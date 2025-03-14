package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "user/manager")
public class ManageController {
    @GetMapping("buscar-libro")
    public String getBuscarLibro(Model modelo){
        modelo.addAttribute("contenido", "Buscar libros");
        modelo.addAttribute("description",
                "En esta página se verán las opciones para realizar una venta." +
                        " Se tendrán que desplejar los libros junto con sus respectivos datos." +
                        " Se incluirá un botón para confirmar venta");
        return "principal/libros";
    }

    @GetMapping("gestionar-libro")
    public String getGestionarLibro(Model modelo){
        modelo.addAttribute("contenido", "Gestionar Libros");
        modelo.addAttribute("description",
                "Se verá una tabla mostrando los libros vendidos, " +
                        "cuantos se vendieron y se inclurán las opciones para generar los reportes de las ventas");
        return "principal/gestion";
    }

    @GetMapping("gestionar-autor")
    public String getGestionarAutor(Model modelo){
        modelo.addAttribute("contenido", "Gestionar Autores");
        modelo.addAttribute("description",
                "Se verá una tabla mostrando los libros vendidos, " +
                        "cuantos se vendieron y se inclurán las opciones para generar los reportes de las ventas");
        return "principal/gestion";
    }

    @GetMapping("gestionar-editoriales")
    public String getGestionarEditorial(Model modelo){
        modelo.addAttribute("contenido", "Gestionar Editoriales");
        modelo.addAttribute("description",
                "Se verá una tabla mostrando los libros vendidos, " +
                        "cuantos se vendieron y se inclurán las opciones para generar los reportes de las ventas");
        return "principal/gestion";
    }

    @GetMapping("gestionar-clasificaciones")
    public String getGestionarClasificacion(Model modelo){
        modelo.addAttribute("contenido", "Gestionar Clasificaciones");
        modelo.addAttribute("description",
                "Se verá una tabla mostrando los libros vendidos, " +
                        "cuantos se vendieron y se inclurán las opciones para generar los reportes de las ventas");
        return "principal/gestion";
    }
}
