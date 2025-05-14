package dgtic.core.service.venta;

import dgtic.core.model.Venta;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> findAll();

    void save(Venta venta);

    Optional<Venta> findById(Integer numVenta);

    List<Venta> findAllByOrderByFechaVentaAsc();

    List<Venta> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);
}
