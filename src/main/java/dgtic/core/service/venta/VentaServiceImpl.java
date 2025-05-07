package dgtic.core.service.venta;

import dgtic.core.model.Venta;
import dgtic.core.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService{
    @Autowired
    VentaRepository ventaRepository;

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(Integer numVenta) {
        return ventaRepository.findById(numVenta);
    }

    @Override
    public List<Venta> findAllByOrderByFechaVentaAsc() {
        return ventaRepository.findAllByOrderByFechaVentaAsc();
    }

    @Override
    public List<Venta> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaVentaBetween(inicio, fin);
    }
}
