package dgtic.core.service;

import dgtic.core.model.Editorial;
import dgtic.core.model.dto.EditorialDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EditorialService {
    List<Editorial> findAll();
    Optional<Editorial> findById(Integer id);
    Optional<Editorial> findByEditorialName(String editorialName);
    void save(Editorial editorial);
    void deleteById(Integer id);

    List<EditorialDto> findEditorialView(String dato);
    Page<Editorial> findPage(Pageable pageable);
    Page<Editorial> findEditorialByName(String editorialName, Pageable pageable);
}
