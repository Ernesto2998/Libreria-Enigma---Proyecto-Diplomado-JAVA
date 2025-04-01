package dgtic.core.service.nacionalidad;

import dgtic.core.model.Nacionalidad;

import java.util.List;
import java.util.Optional;

public interface NacionalidadService {
    List<Nacionalidad> findAll();
    Optional<Nacionalidad> findById(Integer id);
    Optional<Nacionalidad> findByNacionalidad(String nacionalidadName);
    void save(Nacionalidad nacionalidad);
    void deleteById(Integer id);
}
