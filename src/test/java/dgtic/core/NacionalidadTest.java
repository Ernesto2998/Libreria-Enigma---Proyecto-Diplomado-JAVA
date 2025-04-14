package dgtic.core;

import dgtic.core.model.Nacionalidad;
import dgtic.core.repository.NacionalidadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NacionalidadTest {

    @Autowired
    NacionalidadRepository nacionalidadRepository;

    @Test
    void findAllNacionalidadesTest() {
        List<Nacionalidad> nacionalidades = nacionalidadRepository.findAll();
        nacionalidades.forEach(System.out::println);
    }

    @Test
    void findByIdNacionalidadTest() {
        Integer id = 1;

        Optional<Nacionalidad> nacionalidad = nacionalidadRepository.findById(id);

        assertThat(nacionalidad).isPresent();
        assertThat(nacionalidad.get().getId()).isEqualTo(id);

        System.out.println(nacionalidad);
    }

    @Test
    void findByNacionalidadNameTest() {
        String name = "Japonesa";

        Optional<Nacionalidad> nacionalidad = nacionalidadRepository.findByNacionalidadName(name);

        assertThat(nacionalidad).isPresent();
        assertThat(nacionalidad.get().getNacionalidadName()).isEqualTo(name);

        System.out.println(nacionalidad);
    }

    @Test
    void createNacionalidadTest() {
        Nacionalidad nacionalidad = new Nacionalidad();
        nacionalidad.setNacionalidadName("Nacionalidad Test");
        Nacionalidad elementSaved = nacionalidadRepository.save(nacionalidad);

        // Verificar que el nacionalidad tiene un ID generado
        assertThat(elementSaved.getId()).isNotNull();

        Optional<Nacionalidad> nacionalidadCreated = nacionalidadRepository.findById(elementSaved.getId());
        System.out.println(nacionalidadCreated);
    }

    @Test
    void updateClasificacionTest() {
        Integer id = 38;
        String newName = "Nacionalidad Editada";

        Nacionalidad nacionalidad = nacionalidadRepository.findById(id).orElseThrow();
        nacionalidad.setNacionalidadName(newName);
        nacionalidadRepository.save(nacionalidad);

        Nacionalidad updatedNa = nacionalidadRepository.findById(id).orElseThrow();
        assertThat(updatedNa.getNacionalidadName()).isEqualTo(newName);
        System.out.println(updatedNa);
    }

    @Test
    void deleteClasificacionTest() {
        Integer id = 38;

        nacionalidadRepository.deleteById(id);
        Optional<Nacionalidad> nacionalidad = nacionalidadRepository.findById(id);
        assertThat(nacionalidad).isEmpty();

        System.out.println("Nacionalidad borrada: id " + id);
    }
}
