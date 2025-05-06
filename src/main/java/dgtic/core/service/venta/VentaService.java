package dgtic.core.service.venta;

import dgtic.core.model.Venta;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> findAll();

    Optional<Venta> findById(Integer numVenta);

    List<Venta> findAllByOrderByFechaVentaAsc();
}
