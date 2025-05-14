package dgtic.core.repository;

import dgtic.core.model.Inventario;
import dgtic.core.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    @Query("SELECT i FROM Inventario i JOIN i.sucursal s WHERE s.id = :sucursalId ORDER BY s.calle ASC")
    Page<Inventario> findBySucursalId(@Param("sucursalId") Integer sucursalId, Pageable pageable);

    @Query("SELECT i FROM Inventario i JOIN i.libro l WHERE l.id = :libroId ORDER BY l.titulo ASC")
    Page<Inventario> findByLibroId(@Param("libroId") Integer libroId, Pageable pageable);

    Optional<Inventario> findByLibroIdAndSucursalId(Integer libroId, Integer sucursalId);

    @Query("SELECT i.libro FROM Inventario i WHERE i.sucursal.id = :sucursalId AND i.stock > 0 ORDER BY i.libro.titulo ASC")
    List<Libro> getLibrosDisponiblesBySucursal(@Param("sucursalId") Integer sucursalId);
}
