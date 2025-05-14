package dgtic.core.controller;

import dgtic.core.model.*;
import dgtic.core.model.dto.VentaLibroDto;
import dgtic.core.security.EmpleadoAutenticadoService;
import dgtic.core.service.empleado.EmpleadoService;
import dgtic.core.service.inventario.InventarioService;
import dgtic.core.service.libro.LibroService;
import dgtic.core.service.sucursal.SucursalService;
import dgtic.core.service.venta.VentaService;
import dgtic.core.service.ventaLibro.VentaLibroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    InventarioService inventarioService;
    @Autowired
    VentaService ventaService;
    @Autowired
    VentaLibroService ventaLibroService;
    @Autowired
    private EmpleadoAutenticadoService empleadoAutenticadoService;

    @GetMapping("venta")
    public String getRealizarVenta(Model modelo) {

        Empleado empleado = empleadoAutenticadoService.obtenerEmpleadoAutenticado();
        Empleado empleadoTemp = new Empleado();
        empleadoTemp.setNumEmpleado(empleado.getNumEmpleado());
        empleadoTemp.setNombre(empleado.getNombre());
        empleadoTemp.setApellido1(empleado.getApellido1());
        empleadoTemp.setApellido2(empleado.getApellido2());
        empleadoTemp.setSucursal(empleado.getSucursal());

        List<Sucursal> sucursales = sucursalService.findAllByOrderByCalleAsc();
//        List<Libro> libros = libroService.findAllByOrderByTituloAsc();
        List<Libro> libros = inventarioService.getLibrosDisponiblesBySucursal(empleadoTemp.getSucursal().getId());
        List<Empleado> empleados = empleadoService.findAllByOrderByApellido1Asc();

        modelo.addAttribute("contenido", "Realizar Venta");
        modelo.addAttribute("ventaLibro", new VentaLibro());
        modelo.addAttribute("empleados", empleados);
        modelo.addAttribute("sucursales", sucursales);
        modelo.addAttribute("libros", libros);

        if (empleadoTemp != null && empleadoTemp.getSucursal() != null) {
            modelo.addAttribute("sucursalSeleccionada", empleadoTemp.getSucursal().getId());
            modelo.addAttribute("empleadoActual", empleadoTemp);
        }

        return "principal/venta/venta";
    }

    @GetMapping("/verificar-venta")
    public String verificarVenta(@ModelAttribute VentaLibroDto ventaLibroDto, Model model) {
        LocalDateTime fechaHora = ventaLibroDto.getFechaVenta().atTime(LocalTime.now());

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

        // Actualizar inventario
        inventarioService.reduceInventoryByUnit(ventaLibroDto.getLibroId(), ventaLibroDto.getSucursalId(), ventaLibroDto.getCantidad());

        // Añadir venta
        Empleado empleado = empleadoService.findById(ventaLibroDto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("No se encontró Empleado"));
        Venta ventaNew = new Venta();
        ventaNew.setEmpleado(empleado);
        ventaNew.setFechaVenta(ventaLibroDto.getFechaVenta().atTime(LocalTime.now()));
        ventaNew.setMetodoPago(ventaLibroDto.getMetodoPago());
        ventaNew.setTotal(ventaLibroDto.getTotal());
        ventaService.save(ventaNew);
//        Integer ventaId = ventaNew.getNumVenta();

        // Añador Venta-Libro
        Libro libro = libroService.findById(ventaLibroDto.getLibroId())
                .orElseThrow(() -> new RuntimeException("No se encontró Libro"));
        VentaLibro ventaLibro = new VentaLibro();
        ventaLibro.setVenta(ventaNew);
        ventaLibro.setLibro(libro);
        ventaLibro.setCantidadLibros(ventaLibroDto.getCantidad().byteValue());
        ventaLibroService.save(ventaLibro);


        // Agrega info al modelo si vas a mostrar una vista de confirmación

//        model.addAttribute("venta", ventaLibroDto);

        return "redirect:/libreria/venta";
    }

    @GetMapping(value = "libros-por-sucursal/{idSucursal}", produces = "application/json")
    public @ResponseBody List<Libro> obtenerLibrosPorSucursal(@PathVariable Integer idSucursal) {
        return inventarioService.getLibrosDisponiblesBySucursal(idSucursal);
    }
}
