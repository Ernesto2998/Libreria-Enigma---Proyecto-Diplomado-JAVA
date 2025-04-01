package dgtic.core.service.editorial;

import dgtic.core.model.Editorial;
import dgtic.core.model.dto.EditorialDto;
import dgtic.core.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional(readOnly = true)
    public Optional<Editorial> findByEditorialName(String editorialName) {
        return editorialRepository.findByEditorialName(editorialName);
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

    @Override
    public List<EditorialDto> findEditorialView(String dato) {
        return editorialRepository.findEditorialView(dato);
    }

    @Override
    public Page<Editorial> findPage(Pageable pageable) {
        return editorialRepository.findAll(pageable);
    }

    @Override
    public Page<Editorial> findEditorialByName(String editorialName, Pageable pageable) {
        return editorialRepository.findByEditorialNameContainingIgnoreCase(editorialName, pageable);
    }
}
