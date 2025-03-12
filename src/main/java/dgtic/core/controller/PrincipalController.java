package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "spring")
public class PrincipalController {
    @RequestMapping(value = "requestmapping", method = RequestMethod.GET)
    public String mappingClase(Model modelo){
        modelo.addAttribute("contenido", "@RequestMappingMapping en Clase");
        return "principal/requestmapping";
    }

    @GetMapping("getmapping")
    public String getMapping(Model modelo){
        modelo.addAttribute("contenido", "@GetMapping en método");
        return "principal/getmapping";
    }

    @GetMapping("parametro-uno/{edad}")
    public String getparametroUno(@PathVariable("edad") int edad, Model modelo){
        String cadena = "Tu edad es: " + edad  ;
        modelo.addAttribute("contenido", cadena);
        return "principal/pathvariable";
    }

    @GetMapping("parametro-dos/{edad}/{nombre}")
    public String getparametroDosNombre(@PathVariable("edad") int edad,
                                        @PathVariable("nombre") String nombre,
                                        Model modelo){
        String cadena = "Hola "+ nombre +". Tu edad es: " + edad  ;
        modelo.addAttribute("contenido", cadena);
        return "principal/pathvariable";
    }

    @GetMapping("requestparam")
    public String getRequestParam(@RequestParam(value = "dato", required = false) String informacion,
                                  Model modelo){
        String cadena = "Sin información";
        if(informacion != null){
            cadena = "La información es: "+ informacion;
        }
        modelo.addAttribute("contenido", cadena);
        return "principal/requestParam";
    }

    @GetMapping("requestparam-v")
    public String getRequestParametros(@RequestParam(value = "dato", required = false) String informacion,
                                  @RequestParam(value = "diplomado", required = false) String diplo,
                                  Model modelo){
        String cadena = "Sin información";
        if(informacion != null && diplo != null){
            cadena = "La información es: "+ informacion + " diplomado " +diplo;
        }
        modelo.addAttribute("contenido", cadena);
        return "principal/requestParam";
    }
}
