package dgtic.core;

import dgtic.core.model.Venta;
import dgtic.core.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VentaTest {

    @Autowired
    VentaRepository ventaRepository;

    @Test
    @Transactional
    void findAllVentasTest() {
        List<Venta> ventas = ventaRepository.findAll();
        ventas.forEach(System.out::println);
    }

    @Test
    @Transactional
    void findByIdventaTest() {
        Integer id = 5;

        Venta venta = ventaRepository.findById(id).orElseThrow();
        System.out.println(venta);
    }

    @Test
    @Transactional
    void findAllVentasByFechaTest() {
        List<Venta> ventas = ventaRepository.findAllByOrderByFechaVentaAsc();
        ventas.forEach(System.out::println);
    }
}
