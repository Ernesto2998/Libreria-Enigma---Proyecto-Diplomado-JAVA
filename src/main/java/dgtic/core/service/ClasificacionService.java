package dgtic.core.service;

import dgtic.core.model.Clasificacion;

import java.util.List;
import java.util.Optional;

public interface ClasificacionService {
    List<Clasificacion> findAll();
    Optional<Clasificacion> findById(Integer id);
    void save(Clasificacion clasificacion);
    void deleteById(Integer id);
}
