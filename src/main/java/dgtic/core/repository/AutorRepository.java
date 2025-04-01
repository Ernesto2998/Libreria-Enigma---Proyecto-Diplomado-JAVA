package dgtic.core.repository;

import dgtic.core.model.Autor;
import dgtic.core.model.dto.AutorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    @Query("SELECT new dgtic.core.model.dto.AutorDto( " +
            "a.id, " +
            "CONCAT(a.nombre, ' ', a.apellidoUno, ' ', COALESCE(a.apellidoDos, '')), " +
            "a.nacionalidad.nacionalidadName) " +
            "FROM Autor a " +
            "WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            "OR LOWER(a.apellidoUno) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            "OR LOWER(a.apellidoDos) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<AutorDto> findNombreCompletoView(String dato);

    Page<Autor> findByNombreContainingIgnoreCaseOrApellidoUnoContainingIgnoreCaseOrApellidoDosContainingIgnoreCase
            (String nombre, String apellidoUno, String apellidoDos, Pageable pageable);

    Optional<Autor> findByNombreOrApellidoUnoOrApellidoDos
            (@Param("nombre") String nombre,
             @Param("apellidoUno") String apellidoUno,
             @Param("apellidoDos") String apellidoDos);
}
