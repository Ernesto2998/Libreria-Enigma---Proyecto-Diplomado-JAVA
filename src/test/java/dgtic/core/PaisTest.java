package dgtic.core;

import dgtic.core.model.Pais;
import dgtic.core.repository.PaisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PaisTest {

    @Autowired
    PaisRepository paisRepository;

    @Test
    void findAllPaisTest() {
        List<Pais> paises = paisRepository.findAll();
        paises.forEach(System.out::println);
    }
}
