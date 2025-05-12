package dgtic.core.repository;

import java.util.List;
import java.util.Optional;

import dgtic.core.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByNumEmpleado(Integer numEmpleado);

    List<Empleado> findAllByOrderByApellido1Asc();
}