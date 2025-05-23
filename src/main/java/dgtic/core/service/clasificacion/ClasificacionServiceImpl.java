package dgtic.core.service.clasificacion;

import dgtic.core.model.Clasificacion;
import dgtic.core.model.dto.ClasificacionDto;
import dgtic.core.repository.ClasificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClasificacionServiceImpl implements ClasificacionService {
    @Autowired
    ClasificacionRepository clasificacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Clasificacion> findAll() {
        return clasificacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Clasificacion> findById(Integer id) {
        return clasificacionRepository.findById(id);
    }

    @Override
    public Optional<Clasificacion> findByTipoClasificacion(String tipoClasificacion) {
        return clasificacionRepository.findByTipoClasificacion(tipoClasificacion);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Clasificacion> findPage(Pageable pageable) {
        return clasificacionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Clasificacion> findClasificacionByName(String tipoClasificacion, Pageable pageable) {
        return clasificacionRepository.findByTipoClasificacionContainingIgnoreCase(tipoClasificacion, pageable);
    }

    @Override
    @Transactional
    public void save(Clasificacion clasificacion) {
        clasificacionRepository.save(clasificacion);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        clasificacionRepository.deleteById(id);
    }

    @Override
    public List<ClasificacionDto> findClasificacionView(String dato) {
        return clasificacionRepository.findClasificacionView(dato);
    }
}
