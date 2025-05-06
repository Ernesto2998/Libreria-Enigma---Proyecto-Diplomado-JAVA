package dgtic.core.controller;

import dgtic.core.model.Libro;
import dgtic.core.model.Venta;
import dgtic.core.model.VentaLibro;
import dgtic.core.service.libro.LibroService;
import dgtic.core.service.venta.VentaService;
import dgtic.core.service.ventaLibro.VentaLibroService;
import dgtic.core.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "libreria/historial-ventas")
public class HistorialVentasController {
    @Autowired
    VentaLibroService ventaLibroService;
    @Autowired
    VentaService ventaService;
    @Autowired
    LibroService libroService;

    @GetMapping("")
    public String getHistorialVenta(@RequestParam(name = "page", defaultValue = "0") int page,
                                Model modelo) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by("venta.fechaVenta").ascending());
        Page<VentaLibro> pageVentaLibro = ventaLibroService.findPage(pageable);
        RenderPagina<VentaLibro> renderPagina = new RenderPagina<>("historial", pageVentaLibro);

        List<Venta> ventas = ventaService.findAllByOrderByFechaVentaAsc();
        List<Libro> libros = libroService.findAllByOrderByTituloAsc();

//        modelo.addAttribute("hostorialB", new VentaLibro());
//        modelo.addAttribute("sucursales", ventas);
//        modelo.addAttribute("libros", libros);
        modelo.addAttribute("contenido", "Historial de compras");
        modelo.addAttribute("listaHistorial", pageVentaLibro);
        modelo.addAttribute("page", renderPagina);
        return "principal/historial/historial";
    }
}
