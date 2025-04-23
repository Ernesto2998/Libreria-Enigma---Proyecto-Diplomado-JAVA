package dgtic.core.repository;

import dgtic.core.model.Sucursal;
import dgtic.core.model.dto.SucursalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    @Query("select new dgtic.core.model.dto.SucursalDto(s.id, s.calle, s.numeroExterior, s.numeroInterior, s.colonia, s.municipio, s.codigoPostal) " +
            "from Sucursal s where s.calle like %?1%")
    List<SucursalDto> findSucursalViewCalle(String dato);

    @Query("select new dgtic.core.model.dto.SucursalDto(s.id, s.calle, s.numeroExterior, s.numeroInterior, s.colonia, s.municipio, s.codigoPostal) " +
            "from Sucursal s where s.colonia like %?1%")
    List<SucursalDto> findSucursalViewColonia(String dato);

    @Query("select new dgtic.core.model.dto.SucursalDto(s.id, s.calle, s.numeroExterior, s.numeroInterior, s.colonia, s.municipio, s.codigoPostal) " +
            "from Sucursal s where s.municipio like %?1%")
    List<SucursalDto> findSucursalViewMinicipio(String dato);

    Page<Sucursal> findByCalleContainingIgnoreCase(String calle, Pageable pageable);
    Page<Sucursal> findByColoniaContainingIgnoreCase(String colonia, Pageable pageable);
    Page<Sucursal> findByMunicipioContainingIgnoreCase(String municipio, Pageable pageable);
    Page<Sucursal> findByCodigoPostal(Integer cp, Pageable pageable);

    @Query("SELECT s FROM Sucursal s JOIN s.pais p WHERE p.id = :paisId")
    Page<Sucursal> findByPaisId(@Param("paisId") Integer paisId, Pageable pageable);
}
