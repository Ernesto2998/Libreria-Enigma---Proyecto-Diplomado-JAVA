package dgtic.core.controller;

import dgtic.core.model.*;
import dgtic.core.service.libro.LibroService;
import dgtic.core.service.venta.VentaService;
import dgtic.core.service.ventaLibro.VentaLibroService;
import dgtic.core.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping(value = "libreria/historial")
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

    @GetMapping("buscar-historial-tabla")
    public String getHistorialPorFecha(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            Model model) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by("venta.fechaVenta").ascending());
        Page<VentaLibro> pageVentaLibro = ventaLibroService.findPage(pageable);

        if (fechaInicio != null && fechaFin != null) {
            pageVentaLibro = ventaLibroService.findByFechaVentaBetween(
                    fechaInicio.atStartOfDay(), fechaFin.atTime(LocalTime.MAX), pageable
            );
        }
        RenderPagina<VentaLibro> renderPagina = new RenderPagina<>("historial", pageVentaLibro);

        model.addAttribute("contenido", "Historial de compras");
        model.addAttribute("listaHistorial", pageVentaLibro);
        model.addAttribute("page", renderPagina);
        return "principal/historial/historial";
    }
//
//    @PostMapping("buscar-historial-tabla")
//    public String buscarEdotiralTabla(
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "sucursalId", required = false) Integer sucursalId,
//            @RequestParam(name = "libroId", required = false) Integer libroId,
//            Model model) {
//
//        Pageable pageable = PageRequest.of(page, 20, Sort.by("libro.titulo").ascending());
//        Page<Inventario> pageInventarios = inventarioService.findPage(pageable);
////        RenderPagina<Inventario> renderPagina = new RenderPagina<>("inventario", inventarios);
//
//        List<Sucursal> sucursales = sucursalService.findAllByOrderByCalleAsc();
//        List<Libro> libros = libroService.findAllByOrderByTituloAsc();
//
//        if (sucursalId != null) {
//            pageInventarios = inventarioService.findInventarioBySucursalId(sucursalId, pageable);}
//        else if (libroId != null) {
//            pageInventarios = inventarioService.findInventarioByLibroId(libroId, pageable);
//        }
//
//        RenderPagina<Inventario> renderPagina = new RenderPagina<>("/libreria/inventario/buscar-inventario-tabla", pageInventarios);
//
//        model.addAttribute("inventarioB", new Inventario());
//        model.addAttribute("sucursales", sucursales);
//        model.addAttribute("libros", libros);
//        model.addAttribute("sucursalId", sucursalId);
//        model.addAttribute("libroId", libroId);
//        model.addAttribute("contenido", "Inventario");
//        model.addAttribute("listaInventario", pageInventarios);
//        model.addAttribute("page", renderPagina);
//
//        return "principal/inventario/inventario";
//    }
}
