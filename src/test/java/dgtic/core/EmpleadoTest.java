package dgtic.core;

import dgtic.core.model.Empleado;
import dgtic.core.repository.EmpleadoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmpleadoTest {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Test
    @Transactional
    void findAllEmpleadosTest() {
        List<Empleado> empleados = empleadoRepository.findAll();
        empleados.forEach(System.out::println);
    }

    @Test
    @Transactional
    void findAllEmpleadosOrderByApellido1Test() {
        Integer id = 5;

        List<Empleado> empladosByApellido1 = empleadoRepository.findAllByOrderByApellido1Asc();
        empladosByApellido1.forEach(System.out::println);
    }
}
