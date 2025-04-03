package dgtic.core.repository;

import dgtic.core.model.Libro;
import dgtic.core.model.dto.LibroDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    @Query("select new dgtic.core.model.dto.LibroDto(l.id, l.titulo, l.sinopsis, l.precio, l.descuento) " +
            "from Libro l where l.titulo like %?1%")
    List<LibroDto> findLibroView(String dato);

    Page<Libro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    Optional<Libro> findByTitulo(@Param("titulo") String titulo);

}
