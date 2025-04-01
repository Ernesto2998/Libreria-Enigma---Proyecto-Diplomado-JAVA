package dgtic.core.repository;

import dgtic.core.model.Nacionalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NacionalidadRepository extends JpaRepository<Nacionalidad, Integer> {
    Optional<Nacionalidad> findByNacionalidadName(@Param("nacionalidad") String nacionalidad);
}
