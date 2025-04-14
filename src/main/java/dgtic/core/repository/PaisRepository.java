package dgtic.core.repository;

import dgtic.core.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Integer> {
    Optional<Pais> findByNombre(@Param("nombre") String nombre);
}
