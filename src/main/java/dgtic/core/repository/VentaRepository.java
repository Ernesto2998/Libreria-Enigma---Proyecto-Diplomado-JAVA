package dgtic.core.repository;

import dgtic.core.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findAllByOrderByFechaVentaAsc();

    List<Venta> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);
}
