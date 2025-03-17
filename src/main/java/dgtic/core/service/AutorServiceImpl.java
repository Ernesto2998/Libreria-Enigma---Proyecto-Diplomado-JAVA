package dgtic.core.service;

import dgtic.core.model.Autor;
import dgtic.core.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService{
    @Autowired
    AutorRepository autorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Autor> findById(Integer id) {
        return autorRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Autor autor) {
        autorRepository.save(autor);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        autorRepository.deleteById(id);
    }
}
