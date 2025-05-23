package dgtic.core.service.libro;

import dgtic.core.model.Libro;
import dgtic.core.model.dto.LibroDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> findAll();

    Optional<Libro> findById(Integer id);

    void save(Libro libro);

    void deleteById(Integer id);

    List<LibroDto> findLibroView(String dato);

    Page<Libro> findPage(Pageable pageable);

    Page<Libro> findLibroByTitulo(String titulo, Pageable pageable);

    Page<Libro> findLibroByTipoPasta(String titulo, Pageable pageable);

    Page<Libro> findLibroByAutorId(Integer autorId, Pageable pageable);

    Page<Libro> findLibroByClasificacionId(Integer clasificacionId, Pageable pageable);

    Page<Libro> findLibroByEditorialId(Integer editorialId, Pageable pageable);

    List<Libro> findAllByOrderByTituloAsc();
}
