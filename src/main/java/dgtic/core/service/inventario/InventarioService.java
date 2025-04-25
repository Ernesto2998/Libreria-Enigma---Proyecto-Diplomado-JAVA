package dgtic.core.service.inventario;

import dgtic.core.model.Inventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InventarioService {
    List<Inventario> findAll();
    Optional<Inventario> findById(Integer id);

    Page<Inventario> findPage(Pageable pageable);
}
