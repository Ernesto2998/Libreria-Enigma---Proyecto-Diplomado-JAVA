package dgtic.core.controller;

import dgtic.core.model.*;
import dgtic.core.service.ReportesPdf.ReportesPdfService;
import dgtic.core.service.libro.LibroService;
import dgtic.core.service.venta.VentaService;
import dgtic.core.service.ventaLibro.VentaLibroService;
import dgtic.core.util.RenderPagina;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    @Autowired
    ReportesPdfService reportesPdfService;

    @GetMapping("")
    public String getHistorialVenta(@RequestParam(name = "page", defaultValue = "0") int page,
                                Model modelo) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by("venta.fechaVenta").ascending());
        Page<VentaLibro> pageVentaLibro = ventaLibroService.findPage(pageable);
        RenderPagina<VentaLibro> renderPagina = new RenderPagina<>("historial", pageVentaLibro);

        List<Venta> ventas = ventaService.findAllByOrderByFechaVentaAsc();
        List<Libro> libros = libroService.findAllByOrderByTituloAsc();

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
        RenderPagina<VentaLibro> renderPagina = new RenderPagina<>("/libreria/historial/buscar-historial-tabla", pageVentaLibro);

        model.addAttribute("contenido", "Historial de compras");
        model.addAttribute("listaHistorial", pageVentaLibro);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);
        model.addAttribute("page", renderPagina);
        return "principal/historial/historial";
    }

    @PostMapping("buscar-historial-tabla")
    public String postHistorialPorFecha(@RequestParam(name = "page", defaultValue = "0") int page,
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
        RenderPagina<VentaLibro> renderPagina = new RenderPagina<>("/libreria/historial/buscar-historial-tabla", pageVentaLibro);

        model.addAttribute("contenido", "Historial de compras");
        model.addAttribute("listaHistorial", pageVentaLibro);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);
        model.addAttribute("page", renderPagina);
        return "principal/historial/historial";
    }

    @GetMapping("delete-historial/{id}")
    public String eliminarHistorial(@PathVariable("id") Integer id,
                                    RedirectAttributes modelo){
        ventaLibroService.deleteBy(id);
        return "redirect:/libreria/historial";
    }

    @GetMapping("reporte")
    public void generarReportePDF(
            @RequestParam("fechaUno") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaUno,
            @RequestParam("fechaDos") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDos,
            HttpServletResponse response) throws IOException {

        List<Venta> ventas = ventaService.findByFechaVentaBetween(
                fechaUno.atStartOfDay(), fechaDos.atTime(LocalTime.MAX));

        ByteArrayInputStream bis = reportesPdfService.generarReporteVentas(ventas);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_ventas.pdf");
        IOUtils.copy(bis, response.getOutputStream());
    }
}
