package dgtic.core.repository;

import dgtic.core.model.Editorial;
import dgtic.core.model.dto.EditorialDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
    @Query("select new dgtic.core.model.dto.EditorialDto(e.id,e.editorialName) from editorial e where e.editorialName like %?1%")
    List<EditorialDto> findEditorialView(String dato);

    Page<Editorial> findByEditorialNameContainingIgnoreCase(String editorialName, Pageable pageable);

    Optional<Editorial> findByEditorialName(@Param("editorialName") String editorialName);
}
