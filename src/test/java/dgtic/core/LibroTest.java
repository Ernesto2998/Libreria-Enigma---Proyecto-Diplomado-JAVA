package dgtic.core;

import dgtic.core.model.Editorial;
import dgtic.core.model.Libro;
import dgtic.core.repository.EditorialRepository;
import dgtic.core.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LibroTest {

    @Autowired
    LibroRepository libroRepository;
    @Autowired
    EditorialRepository editorialRepository;

    @Test
    void findAllLibrosTest() {
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    @Test
    void findByIdLibroTest() {
        Integer id = 1;

        Optional<Libro> libro = libroRepository.findById(id);

        assertThat(libro).isPresent();
        assertThat(libro.get().getId()).isEqualTo(id);

        System.out.println(libro);
    }

    @Test
    void createNacionalidadTest() {
        Libro nuevoLibro = new Libro();
        Libro elementSaved = libroRepository.save(nuevoLibro);

        // Verificar que el libro tiene un ID generado
        assertThat(elementSaved.getId()).isNotNull();

        Optional<Libro> libroCreated = libroRepository.findById(elementSaved.getId());
        System.out.println(libroCreated);
    }

    @Test
    void updateClasificacionTest() {
        Integer id = 38;
        String newTitulo = "Libro Editada";
        Double newPrecio = 199.99;
        Integer newDescuento = 50;
        Editorial newEditorial = editorialRepository.findById(1).orElseThrow();

        Libro libro = libroRepository.findById(id).orElseThrow();
        libro.setTitulo(newTitulo);
        libro.setTipoPasta(newTitulo);
        libro.setSinopsis(newTitulo);
        libro.setPrecio(newPrecio);
        libro.setDescuento(newDescuento);
        libro.setEditorial(newEditorial); // Editorial
//        libro.setClasificaciones(newName); // Clasificacion
//        libro.setAutores(newName); // Autores
        libroRepository.save(libro);

        Libro updatedL = libroRepository.findById(id).orElseThrow();
        assertThat(updatedL.getTitulo()).isEqualTo(newTitulo);
        assertThat(updatedL.getTipoPasta()).isEqualTo(newTitulo);
        assertThat(updatedL.getSinopsis()).isEqualTo(newTitulo);
        assertThat(updatedL.getPrecio()).isEqualTo(newPrecio);
        assertThat(updatedL.getDescuento()).isEqualTo(newDescuento);
        assertThat(updatedL.getEditorial()).isEqualTo(newEditorial.getEditorialName());

        System.out.println(updatedL);
    }

    @Test
    void deleteClasificacionTest() {
        Integer id = 38;

        libroRepository.deleteById(id);
        Optional<Libro> libro = libroRepository.findById(id);
        assertThat(libro).isEmpty();

        System.out.println("Libro eliminado: id " + id);
    }
}
