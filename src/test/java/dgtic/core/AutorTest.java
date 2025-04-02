package dgtic.core;

import dgtic.core.model.Autor;
import dgtic.core.model.Nacionalidad;
import dgtic.core.model.dto.AutorDto;
import dgtic.core.repository.AutorRepository;
import dgtic.core.repository.NacionalidadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AutorTest {

    @Autowired
    NacionalidadRepository nacionalidadRepository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    void findAllAutoresTest() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    @Test
    void findByIdAutorTest() {
        Integer id = 1;

        Optional<Autor> autor = autorRepository.findById(id);

        assertThat(autor).isPresent();
        assertThat(autor.get().getId()).isEqualTo(id);

        System.out.println(autor);
    }

    @Test
    void findByNombreOrApellidoUnoOrApellidoDosTest() {
        String name = "Gabriel";

        Optional<Autor> autor = autorRepository.findByNombreOrApellidoUnoOrApellidoDos(name, name, name);

        assertThat(autor)
                .isPresent()
                .get()
                .extracting(Autor::getNombre, Autor::getApellidoUno, Autor::getApellidoDos)
                .anyMatch(name::equals);

        System.out.println(autor);
    }

    @Test
    void findByNombreOrApellidoUnoOrApellidoDosContainingIgnoreCaseTest() {
        String name = "Ma";
        Pageable pageable = PageRequest.of(0, 10);

        Page<Autor> autorPage = autorRepository.findByNombreContainingIgnoreCaseOrApellidoUnoContainingIgnoreCaseOrApellidoDosContainingIgnoreCase(name, name, name, pageable);

        // Ensure the page is not empty
        assertThat(autorPage.getContent()).isNotEmpty();

        // Verify that at least one Autor matches the search criteria
//        assertThat(autorPage.getContent())
//                .extracting(Autor::getNombre, Autor::getApellidoUno, Autor::getApellidoDos)
//                .flatExtracting(nombre -> List.of(nombre))
//                .anyMatch(value -> value.toString().equalsIgnoreCase(name));

        // Print results (optional)
        autorPage.forEach(System.out::println);
    }

    @Test
    void findNombreCompletoViewTest() {
        String name = "Gabriel";

        List<AutorDto> autores = autorRepository.findNombreCompletoView(name);

        autores.stream()
                .map(autor -> "ID: " + autor.getId()
                        + ", Nombre Completo: " + autor.getNombreCompleto()
                        + ", Nacionalidad: " + autor.getNacionalidad())
                .forEach(System.out::println);
    }

    @Test
    void createAutorTest() {
        Optional<Nacionalidad> nacionalidad = nacionalidadRepository.findById(28);

        Autor autor = new Autor("Nombre", "Peterno", "Materno", nacionalidad.get());
        Autor elementSaved = autorRepository.save(autor);

        // Verificar que el nacionalidad tiene un ID generado
        assertThat(elementSaved.getId()).isNotNull();

        Optional<Autor> autorCreated = autorRepository.findById(elementSaved.getId());
        System.out.println(autorCreated);
    }

    @Test
    void updateAutorTest() {
        Integer id = 16;
        String newName = "Name E";
        String newPaterno = "Paterno E";
        String newMaterno = "Materno E";
        Nacionalidad nacionalidad = nacionalidadRepository.findById(35).orElseThrow();

        Autor autor = autorRepository.findById(id).orElseThrow();
        autor.setNombre(newName);
        autor.setApellidoUno(newPaterno);
        autor.setApellidoDos(newMaterno);
        autor.setNacionalidad(nacionalidad);
        autorRepository.save(autor);

        Autor updatedAu = autorRepository.findById(id).orElseThrow();
        assertThat(updatedAu.getNombre()).isEqualTo(newName);
        assertThat(updatedAu.getApellidoUno()).isEqualTo(newPaterno);
        assertThat(updatedAu.getApellidoDos()).isEqualTo(newMaterno);
        assertThat(updatedAu.getNacionalidad().getNacionalidadName())
                .isEqualTo(nacionalidad.getNacionalidadName());
        System.out.println(updatedAu);
    }

    @Test
    void deleteAutorTest() {
        Integer id = 16;

        autorRepository.deleteById(id);
        Optional<Autor> autor = autorRepository.findById(id);
        assertThat(autor).isEmpty();

        System.out.println("Autor borrado: id " + id);
    }
}
