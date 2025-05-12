package dgtic.core.service.autentificacion;

import dgtic.core.model.Empleado;
import dgtic.core.repository.EmpleadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public boolean authenticate(Integer numEmpleado, String contrasenia) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(numEmpleado);
        log.info("Autenticando al empleado con número: {}", numEmpleado);   // mensajes normales que sí quieres ver en producción

        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();

            log.debug("Resultado de autenticación: {}", true);  // mensajes detallados para depuración
            return BCrypt.checkpw(contrasenia, empleado.getContrasenia());
        }

         log.debug("Resultado de autenticación: {}", true);      // // mensajes detallados para depuración
        return false;
    }

    @Override
    public String getNivelAcceso(Integer numEmpleado) {
        return empleadoRepository.findById(numEmpleado)
                .map(e -> e.getNivelAcceso().name())
                .orElse("Desconocido");
    }
}
