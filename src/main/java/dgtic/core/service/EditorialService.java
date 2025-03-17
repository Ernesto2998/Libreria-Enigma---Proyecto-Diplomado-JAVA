package dgtic.core.service;

import dgtic.core.model.Editorial;

import java.util.List;
import java.util.Optional;

public interface EditorialService {
    List<Editorial> findAll();
    Optional<Editorial> findById(Integer id);
    void save(Editorial editorial);
    void deleteById(Integer id);
}
