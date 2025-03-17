package dgtic.core.service;

import dgtic.core.model.Autor;
import dgtic.core.model.Editorial;
import dgtic.core.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServiceImpl implements EditorialService{
    @Autowired
    EditorialRepository editorialRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Editorial> findAll() {
        return editorialRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Editorial> findById(Integer id) {
        return editorialRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Editorial editorial) {
        editorialRepository.save(editorial);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        editorialRepository.deleteById(id);
    }
}
