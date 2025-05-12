package dgtic.core.service.empleado;

import dgtic.core.model.Empleado;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> findAll();

    List<Empleado> findAllByOrderByApellido1Asc();
}
