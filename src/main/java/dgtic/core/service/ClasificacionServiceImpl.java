package dgtic.core.service;

import dgtic.core.model.Clasificacion;
import dgtic.core.repository.ClasificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClasificacionServiceImpl implements ClasificacionService{
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
    @Transactional
    public void save(Clasificacion clasificacion) {
        clasificacionRepository.save(clasificacion);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        clasificacionRepository.deleteById(id);
    }
}
