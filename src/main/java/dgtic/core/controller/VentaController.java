package dgtic.core.controller;

import dgtic.core.model.Empleado;
import dgtic.core.model.Libro;
import dgtic.core.model.Sucursal;
import dgtic.core.model.VentaLibro;
import dgtic.core.model.dto.VentaLibroDto;
import dgtic.core.service.empleado.EmpleadoService;
import dgtic.core.service.libro.LibroService;
import dgtic.core.service.sucursal.SucursalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "libreria")
public class VentaController {
    @Autowired
    SucursalService sucursalService;
    @Autowired
    LibroService libroService;
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("venta")
    public String getRealizarVenta(Model modelo){

        List<Sucursal> sucursales = sucursalService.findAllByOrderByCalleAsc();
        List<Libro> libros = libroService.findAllByOrderByTituloAsc();
        List<Empleado> empleados = empleadoService.findAllByOrderByApellido1Asc();

        modelo.addAttribute("contenido", "Realizar Venta");
        modelo.addAttribute("ventaLibro", new VentaLibro());
        modelo.addAttribute("empleados", empleados);
        modelo.addAttribute("sucursales", sucursales);
        modelo.addAttribute("libros", libros);
        return "principal/venta/venta";
    }

    @GetMapping("/verificar-venta")
    public String verificarVenta(@ModelAttribute VentaLibroDto ventaLibroDto, Model model) {
        // Aquí puedes hacer lo que necesites con los datos
        // Por ejemplo: calcular el total, validar, guardar, etc.

        // Para ejemplo, puedes imprimirlo
        log.info("Venta recibida:");
        log.info("Sucursal ID: {}", ventaLibroDto.getSucursalId());
        log.info("Num Empleado: {}", ventaLibroDto.getEmpleadoId());
        log.info("Libro ID: {}", ventaLibroDto.getLibroId());
        log.info("Fecha: {}", ventaLibroDto.getFechaVenta());
        log.info("Precio: {}", ventaLibroDto.getPrecio());
        log.info("Descuento: {}", ventaLibroDto.getDescuento());
        log.info("Cantidad: {}", ventaLibroDto.getCantidad());
        log.info("Método de pago: {}",  ventaLibroDto.getMetodoPago());
        log.info("Total: {}", ventaLibroDto.getTotal());

        // Agrega info al modelo si vas a mostrar una vista de confirmación
        model.addAttribute("venta", ventaLibroDto);

        return "venta/confirmacion"; // Cambia esto a la vista que quieras mostrar después
    }
}
