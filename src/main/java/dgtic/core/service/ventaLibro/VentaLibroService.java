package dgtic.core.service.ventaLibro;

import dgtic.core.model.VentaLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VentaLibroService {
    List<VentaLibro> findAll();

    Optional<VentaLibro> findById(Integer id);

    Page<VentaLibro> findPage(Pageable pageable);
}
