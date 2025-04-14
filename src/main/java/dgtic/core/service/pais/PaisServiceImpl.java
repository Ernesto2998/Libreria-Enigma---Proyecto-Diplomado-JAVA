package dgtic.core.service.pais;

import dgtic.core.model.Pais;
import dgtic.core.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaisServiceImpl implements PaisService{
    @Autowired
    PaisRepository paisRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pais> findById(Integer id) {
        return paisRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Pais pais) {
        paisRepository.save(pais);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        paisRepository.deleteById(id);
    }
}
