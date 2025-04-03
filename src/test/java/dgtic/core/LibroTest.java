package dgtic.core;

import dgtic.core.model.Autor;
import dgtic.core.model.Clasificacion;
import dgtic.core.model.Editorial;
import dgtic.core.model.Libro;
import dgtic.core.repository.AutorRepository;
import dgtic.core.repository.ClasificacionRepository;
import dgtic.core.repository.EditorialRepository;
import dgtic.core.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LibroTest {

    @Autowired
    LibroRepository libroRepository;
    @Autowired
    EditorialRepository editorialRepository;
    @Autowired
    ClasificacionRepository clasificacionRepository;
    @Autowired
    AutorRepository autorRepository;

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
        String newTitulo = "Libro Nuevo";
        Float newPrecio = 99.99F;
        Integer newDescuento = 25;
        String newTipoPasta = "Dura";
        String newSinopsis = "Una nueva sinopsis";


        Editorial newEditorial = editorialRepository.findById(1).orElseThrow();
        List<Clasificacion> clasificaciones = new ArrayList<>(
                Arrays.asList(
                        clasificacionRepository.findById(1).orElseThrow(),
                        clasificacionRepository.findById(2).orElseThrow()
                )
        );
        List<Autor> autores = new ArrayList<>(
                Arrays.asList(
                        autorRepository.findById(1).orElseThrow(),
                        autorRepository.findById(2).orElseThrow()
                )
        );
        
        Libro libro = new Libro();
        
        libro.setTitulo(newTitulo);
        libro.setTipoPasta(newTipoPasta);
        libro.setSinopsis(newSinopsis);
        libro.setPrecio(newPrecio);
        libro.setDescuento(newDescuento);
        libro.setEditorial(newEditorial); // Editorial
        libro.setClasificaciones(clasificaciones); // Clasificacion
        libro.setAutores(autores); // Autores

        Libro elementSaved = libroRepository.save(libro);

        Libro updatedL = libroRepository.findById(elementSaved.getId()).orElseThrow();
        assertThat(updatedL.getTitulo()).isEqualTo(newTitulo);
        assertThat(updatedL.getTipoPasta()).isEqualTo(newTipoPasta);
        assertThat(updatedL.getSinopsis()).isEqualTo(newSinopsis);
        assertThat(updatedL.getPrecio()).isEqualTo(newPrecio);
        assertThat(updatedL.getDescuento()).isEqualTo(newDescuento);

        Optional<Libro> libroCreated = libroRepository.findById(elementSaved.getId());
        System.out.println(libroCreated);
    }

    @Test
    void updateClasificacionTest() {
        Integer id = 31;
        String newTitulo = "Libro Editado";
        Float newPrecio = 120.99F;
        Integer newDescuento = 50;
        String newTipoPasta = "Blanda";
        String newSinopsis = "Sinopsis Editada";

        Editorial newEditorial = editorialRepository.findById(2).orElseThrow();
        List<Clasificacion> clasificaciones = new ArrayList<>(
                Arrays.asList(
                        clasificacionRepository.findById(3).orElseThrow(),
                        clasificacionRepository.findById(4).orElseThrow()
                )
        );
        List<Autor> autores = new ArrayList<>(
                Arrays.asList(
                        autorRepository.findById(3).orElseThrow(),
                        autorRepository.findById(4).orElseThrow()
                )
        );

        Libro libro = libroRepository.findById(id).orElseThrow();
        libro.setTitulo(newTitulo);
        libro.setTipoPasta(newTipoPasta);
        libro.setSinopsis(newSinopsis);
        libro.setPrecio(newPrecio);
        libro.setDescuento(newDescuento);
        libro.setEditorial(newEditorial); // Editorial
        libro.setClasificaciones(clasificaciones); // Clasificacion
        libro.setAutores(autores); // Autores

        libroRepository.save(libro);

        Libro updatedL = libroRepository.findById(id).orElseThrow();
        assertThat(updatedL.getTitulo()).isEqualTo(newTitulo);
        assertThat(updatedL.getTipoPasta()).isEqualTo(newTipoPasta);
        assertThat(updatedL.getSinopsis()).isEqualTo(newSinopsis);
        assertThat(updatedL.getPrecio()).isEqualTo(newPrecio);
        assertThat(updatedL.getDescuento()).isEqualTo(newDescuento);

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
