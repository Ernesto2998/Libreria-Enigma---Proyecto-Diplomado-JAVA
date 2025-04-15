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
//    Page<Sucursal> findSucursalByName(String name, Pageable pageable);
}
