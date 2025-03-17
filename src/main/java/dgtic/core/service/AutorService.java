package dgtic.core.service;

import dgtic.core.model.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    List<Autor> findAll();
    Optional<Autor> findById(Integer id);
    void save(Autor autor);
    void deleteById(Integer id);
}
