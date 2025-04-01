package dgtic.core.service.autor;

import dgtic.core.model.Autor;
import dgtic.core.model.dto.AutorDto;
import dgtic.core.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<AutorDto> findAutorView(String dato) {
        return autorRepository.findNombreCompletoView(dato);
    }

    @Override
    public Page<Autor> findPage(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    @Override
    public Page<Autor> findByNombreOrApellidoUnoOrApellidoDos(String name, String apellidoUno, String apellidoDos, Pageable pageable) {
        return autorRepository
                .findByNombreContainingIgnoreCaseOrApellidoUnoContainingIgnoreCaseOrApellidoDosContainingIgnoreCase(
                        name, apellidoUno, apellidoDos, pageable
                );
    }
}
