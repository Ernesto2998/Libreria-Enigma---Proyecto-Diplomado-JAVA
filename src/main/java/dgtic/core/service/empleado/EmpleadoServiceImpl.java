package dgtic.core.service.empleado;


import dgtic.core.model.Empleado;
import dgtic.core.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public List<Empleado> findAllByOrderByApellido1Asc() {
        return empleadoRepository.findAllByOrderByApellido1Asc();
    }
}
