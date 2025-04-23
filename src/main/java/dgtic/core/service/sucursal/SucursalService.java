package dgtic.core.service.sucursal;

import dgtic.core.model.Sucursal;
import dgtic.core.model.dto.SucursalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SucursalService {
    List<Sucursal> findAll();
    Optional<Sucursal> findById(Integer id);
    void save(Sucursal sucursal);
    void deleteById(Integer id);

    List<SucursalDto> findSucursalViewCalle(String dato);
    List<SucursalDto> findSucursalViewColonia(String dato);
    List<SucursalDto> findSucursalViewMunicipio(String dato);
    Page<Sucursal> findPage(Pageable pageable);
    Page<Sucursal> findSucursalByCalle(String calle, Pageable pageable);
    Page<Sucursal> findSucursalByColonia(String colonia, Pageable pageable);
    Page<Sucursal> findSucursalByMunicipio(String municipio, Pageable pageable);
    Page<Sucursal> findSucursalByCodigoPostal(Integer cp, Pageable pageable);
    Page<Sucursal> findSucursalByPaisId(Integer paisId, Pageable pageable);
}
