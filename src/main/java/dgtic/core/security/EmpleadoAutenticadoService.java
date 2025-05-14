package dgtic.core.security;

import dgtic.core.model.Empleado;
import dgtic.core.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoAutenticadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado obtenerEmpleadoAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Integer numEmpleado) {
            return empleadoRepository.findById(numEmpleado).orElse(null);
        }
        return null;
    }
}