package dgtic.core.model;

import dgtic.core.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventarioTest {

    @Autowired
    InventarioRepository inventarioRepository;

    @Test
    void findAllInventariosTest() {
        List<Inventario> inventarios = inventarioRepository.findAll();
        inventarios.forEach(System.out::println);
    }

    @Test
    void getLibro() {
    }

    @Test
    void getSucursal() {
    }

    @Test
    void getStock() {
    }
}