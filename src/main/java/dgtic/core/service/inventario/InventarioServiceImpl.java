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

@Service
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
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

}
