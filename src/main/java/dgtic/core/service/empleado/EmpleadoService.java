package dgtic.core.service.empleado;

import dgtic.core.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAll();

    Optional<Empleado> findById(Integer id);

    List<Empleado> findAllByOrderByApellido1Asc();
}
