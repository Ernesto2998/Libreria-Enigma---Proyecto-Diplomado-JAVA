package dgtic.core.service.pais;

import dgtic.core.model.Pais;

import java.util.List;
import java.util.Optional;

public interface PaisService {
    List<Pais> findAll();
    Optional<Pais> findById(Integer id);
    void save(Pais pais);
    void deleteById(Integer id);
}
