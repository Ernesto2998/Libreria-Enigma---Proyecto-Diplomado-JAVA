package dgtic.core.service.autentificacion;

public interface AuthService {
    boolean authenticate(Integer numEmpleado, String contrasenia);
    String getNivelAcceso(Integer numEmpleado);
}
