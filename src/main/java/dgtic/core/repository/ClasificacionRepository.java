package dgtic.core.repository;


import dgtic.core.model.Clasificacion;
import dgtic.core.model.dto.ClasificacionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, Integer> {
    @Query("select new dgtic.core.model.dto.ClasificacionDto(cl.id,cl.tipoClasificacion) from clasificacion cl where cl.tipoClasificacion like %?1%")
    List<ClasificacionDto> findEspecieView(String dato);

    Page<Clasificacion> findByTipoClasificacionContainingIgnoreCase(String tipoClasificacion, Pageable pageable);

}
