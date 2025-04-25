package dgtic.core.service.sucursal;

import dgtic.core.model.Sucursal;
import dgtic.core.model.dto.SucursalDto;
import dgtic.core.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements SucursalService{

    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public List<Sucursal> findAllByOrderByCalleAsc() {
        return sucursalRepository.findAllByOrderByCalleAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sucursal> findById(Integer id) {
        return sucursalRepository.findById(id);
    }

    @Override
    public void save(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }

    @Override
    public void deleteById(Integer id) {
        sucursalRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SucursalDto> findSucursalViewCalle(String dato) {
        return sucursalRepository.findSucursalViewCalle(dato);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SucursalDto> findSucursalViewColonia(String dato) {
        return sucursalRepository.findSucursalViewColonia(dato);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SucursalDto> findSucursalViewMunicipio(String dato) {
        return sucursalRepository.findSucursalViewMinicipio(dato);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sucursal> findPage(Pageable pageable) {
        return sucursalRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sucursal> findSucursalByCalle(String calle, Pageable pageable) {
        return sucursalRepository.findByCalleContainingIgnoreCase(calle, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sucursal> findSucursalByColonia(String colonia, Pageable pageable) {
        return sucursalRepository.findByColoniaContainingIgnoreCase(colonia, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sucursal> findSucursalByMunicipio(String municipio, Pageable pageable) {
        return sucursalRepository.findByMunicipioContainingIgnoreCase(municipio, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sucursal> findSucursalByCodigoPostal(Integer cp, Pageable pageable) {
        return sucursalRepository.findByCodigoPostal(cp, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sucursal> findSucursalByPaisId(Integer paisId, Pageable pageable) {
        return sucursalRepository.findByPaisId(paisId, pageable);
    }
}
