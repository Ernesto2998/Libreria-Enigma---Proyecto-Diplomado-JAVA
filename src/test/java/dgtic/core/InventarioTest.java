package dgtic.core;

import dgtic.core.model.Inventario;
import dgtic.core.model.Libro;
import dgtic.core.model.Nacionalidad;
import dgtic.core.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InventarioTest {

    @Autowired
    InventarioRepository inventarioRepository;

    @Test
    void getLibrosBySucursalTest() {
        Integer id = 1;

        List<Libro> libros = inventarioRepository.getLibrosDisponiblesBySucursal(id);
        libros.forEach(System.out::println);
    }

    @Test
    void findByLibroIdAndSucursalIdTest() {
        Integer libroId = 2;
        Integer sucursalId = 2;

        Inventario inventario = inventarioRepository.findByLibroIdAndSucursalId(libroId, sucursalId).orElseThrow();

        System.out.printf(inventario.toString());
    }
}
