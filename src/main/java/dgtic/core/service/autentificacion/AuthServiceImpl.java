package dgtic.core.service.autentificacion;

import dgtic.core.model.Empleado;
import dgtic.core.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public boolean authenticate(Integer numEmpleado, String contrasenia) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(numEmpleado);

        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            return BCrypt.checkpw(contrasenia, empleado.getContrasenia());
        }

        return false;
    }

    @Override
    public String getNivelAcceso(Integer numEmpleado) {
        return empleadoRepository.findById(numEmpleado)
                .map(e -> e.getNivelAcceso().name())
                .orElse("Desconocido");
    }
}
