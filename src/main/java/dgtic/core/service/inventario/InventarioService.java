package dgtic.core.service.inventario;

import dgtic.core.model.Inventario;
import dgtic.core.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InventarioService {
    List<Inventario> findAll();

    void save(Inventario inventario);

    Optional<Inventario> findById(Integer id);

    Page<Inventario> findPage(Pageable pageable);

    Page<Inventario> findInventarioBySucursalId(Integer sucursalId, Pageable pageable);

    Page<Inventario> findInventarioByLibroId(Integer libroId, Pageable pageable);

    Optional<Inventario> findByLibroIdAndSucursalId(Integer libroId, Integer sucursalId);

    List<Libro> getLibrosDisponiblesBySucursal(Integer idSucursal);

    void reduceInventoryByUnit(Integer libroId, Integer sucursalId, Integer unit);

    void increaseInventoryByUnit(Integer libroId, Integer sucursalId, Integer unit);
}
