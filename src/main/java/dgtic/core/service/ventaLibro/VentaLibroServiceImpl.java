package dgtic.core.service.ventaLibro;

import dgtic.core.model.VentaLibro;
import dgtic.core.repository.VentaLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaLibroServiceImpl implements VentaLibroService{
    @Autowired
    VentaLibroRepository ventaLibroRepository;

    @Override
    public List<VentaLibro> findAll() {
        return ventaLibroRepository.findAll();
    }

    @Override
    public Optional<VentaLibro> findById(Integer id) {
        return ventaLibroRepository.findById(id);
    }

    @Override
    public Page<VentaLibro> findPage(Pageable pageable) {
        return ventaLibroRepository.findAll(pageable);
    }
}
