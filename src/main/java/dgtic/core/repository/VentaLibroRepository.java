package dgtic.core.repository;

import dgtic.core.model.VentaLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaLibroRepository extends JpaRepository<VentaLibro, Integer> {
    @Query("SELECT vl FROM VentaLibro vl WHERE vl.venta.fechaVenta BETWEEN :inicio AND :fin")
    Page<VentaLibro> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin, Pageable pageable);
}
