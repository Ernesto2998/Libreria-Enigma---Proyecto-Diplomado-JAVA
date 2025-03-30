package dgtic.core.service.clasificacion;

import dgtic.core.model.Clasificacion;
import dgtic.core.model.dto.ClasificacionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClasificacionService {
    List<Clasificacion> findAll();
    Optional<Clasificacion> findById(Integer id);
    Optional<Clasificacion> findByTipoClasificacion(String tipoClasificacion);
    Page<Clasificacion> findPage(Pageable pageable);
    Page<Clasificacion> findClasificacionByName(String tipoClasificacion, Pageable pageable);
    void save(Clasificacion clasificacion);
    void deleteById(Integer id);
    List<ClasificacionDto> findEspecieView(String dato);
}
