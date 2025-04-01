package dgtic.core.service.nacionalidad;

import dgtic.core.model.Nacionalidad;
import dgtic.core.repository.NacionalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NacionalidadServiceImpl implements NacionalidadService{
    @Autowired
    NacionalidadRepository nacionalidadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Nacionalidad> findAll() {
        return nacionalidadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Nacionalidad> findById(Integer id) {
        return nacionalidadRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Nacionalidad> findByNacionalidad(String nacionalidadName) {
        return nacionalidadRepository.findByNacionalidadName(nacionalidadName);
    }

    @Override
    @Transactional
    public void save(Nacionalidad nacionalidad) {
        nacionalidadRepository.save(nacionalidad);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        nacionalidadRepository.deleteById(id);
    }
}
