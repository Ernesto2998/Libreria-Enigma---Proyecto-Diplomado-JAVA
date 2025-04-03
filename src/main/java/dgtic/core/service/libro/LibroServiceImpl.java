package dgtic.core.service.libro;

import dgtic.core.model.Libro;
import dgtic.core.model.dto.LibroDto;
import dgtic.core.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService{
    @Autowired
    LibroRepository libroRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Libro> findById(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    @Override
    public void deleteById(Integer id) {
        libroRepository.deleteById(id);
    }

    @Override
    public List<LibroDto> findLibroView(String dato) {
        return libroRepository.findLibroView(dato);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> findPage(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> findLibroByTitulo(String titulo, Pageable pageable) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo, pageable);
    }
}
