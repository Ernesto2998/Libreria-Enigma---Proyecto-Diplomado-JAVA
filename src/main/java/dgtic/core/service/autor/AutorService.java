package dgtic.core.service.autor;

import dgtic.core.model.Autor;
import dgtic.core.model.dto.AutorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    List<Autor> findAll();
    Optional<Autor> findById(Integer id);
    void save(Autor autor);
    void deleteById(Integer id);

    List<AutorDto> findAutorView(String dato);
    Page<Autor> findPage(Pageable pageable);
    Page<Autor> findByNombreOrApellidoUnoOrApellidoDos(
            String name,
            String apellidoUno,
            String apellidoDos,
            Pageable pageable
    );

    Page<Autor> searchAutor(String input,Pageable pageable);

}
