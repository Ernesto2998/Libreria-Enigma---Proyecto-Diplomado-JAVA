package dgtic.core.service.empleado;


import dgtic.core.model.Empleado;
import dgtic.core.repository.EmpleadoRepository;
import dgtic.core.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> findById(Integer id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public List<Empleado> findAllByOrderByApellido1Asc() {
        return empleadoRepository.findAllByOrderByApellido1Asc();
    }


    public Empleado obtenerEmpleadoAutenticado(HttpServletRequest request) {
        String token = extraerTokenDesdeCookies(request);
        if (token != null && jwtService.validateToken(token)) {
            Integer numEmpleado = jwtService.extractNumEmpleado(token);
            return empleadoRepository.findById(numEmpleado).orElse(null);
        }
        return null;
    }

    private String extraerTokenDesdeCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwtToken".equals(cookie.getName())) {  // El nombre debe coincidir con el usado al hacer login
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
