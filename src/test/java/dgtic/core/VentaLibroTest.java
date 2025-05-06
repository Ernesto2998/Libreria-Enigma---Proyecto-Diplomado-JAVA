package dgtic.core;

import dgtic.core.model.VentaLibro;
import dgtic.core.repository.VentaLibroRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VentaLibroTest {

    @Autowired
    VentaLibroRepository ventaLibroRepository;

    @Test
    @Transactional
    void findAllVentaLibrossTest() {
        List<VentaLibro> ventaLibros = ventaLibroRepository.findAll();
        ventaLibros.forEach(System.out::println);
    }

    @Test
    @Transactional
    void findByIdVentaLibroTest() {
        Integer id = 5;

        VentaLibro ventaLibro = ventaLibroRepository.findById(id).orElseThrow();
        System.out.println(ventaLibro);
    }
}
