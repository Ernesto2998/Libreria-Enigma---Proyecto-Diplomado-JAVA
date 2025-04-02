package dgtic.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "libreria/gestionar/libro")
public class ManagerLibroController {
    @Autowired
    MessageSource mensaje;
}
