package dgtic.core.service.inventario;

import dgtic.core.model.Inventario;
import dgtic.core.model.Libro;
import dgtic.core.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    @Override
    public void save(Inventario inventario) {
        inventarioRepository.save(inventario);
    }

    @Override
    public Optional<Inventario> findById(Integer id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public Page<Inventario> findPage(Pageable pageable) {
        return inventarioRepository.findAll(pageable);
    }

    @Override
    public Page<Inventario> findInventarioBySucursalId(Integer sucursalId, Pageable pageable) {
        return inventarioRepository.findBySucursalId(sucursalId, pageable);
    }

    @Override
    public Page<Inventario> findInventarioByLibroId(Integer libroId, Pageable pageable) {
        return inventarioRepository.findByLibroId(libroId, pageable);
    }

    @Override
    public Optional<Inventario> findByLibroIdAndSucursalId(Integer libroId, Integer sucursalId) {
        return inventarioRepository.findByLibroIdAndSucursalId(libroId, sucursalId);
    }

    @Override
    public List<Libro> getLibrosDisponiblesBySucursal(Integer idSucursal) {
        return inventarioRepository.getLibrosDisponiblesBySucursal(idSucursal);
    }

    @Override
    public void reduceInventoryByUnit(Integer libroId, Integer sucursalId, Integer unit) {
        Inventario inventario = inventarioRepository
                .findByLibroIdAndSucursalId(libroId, sucursalId)
                .orElseThrow(() -> new RuntimeException("No se encontró inventario para ese libro y sucursal."));

        if (inventario.getStock() <= 0) {
            throw new RuntimeException("No hay stock disponible para este libro en la sucursal.");
        }

        if (inventario.getStock() - unit < 0) {
            throw new RuntimeException("No hay stock suficiente para este libro en la sucursal.");
        }

        inventario.setStock(inventario.getStock() - unit);
        inventarioRepository.save(inventario);
    }

    @Override
    public void increaseInventoryByUnit(Integer libroId, Integer sucursalId, Integer unit) {
        Inventario inventario = inventarioRepository
                .findByLibroIdAndSucursalId(libroId, sucursalId)
                .orElseThrow(() -> new RuntimeException("No se encontró inventario para ese libro y sucursal."));

        inventario.setStock(inventario.getStock() + unit);
        inventarioRepository.save(inventario);
    }

}
