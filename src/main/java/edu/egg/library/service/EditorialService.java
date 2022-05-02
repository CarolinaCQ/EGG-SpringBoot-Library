package edu.egg.library.service;

import edu.egg.library.entity.Editorial;
import edu.egg.library.repository.EditorialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {

    private final EditorialRepository editorialRepository;

    @Autowired
    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @Transactional
    public void create(Editorial editorial2) {
        Editorial editorial = new Editorial();

        editorial.setName(editorial2.getName());
        editorial.setLow(false);
        
        editorialRepository.save(editorial);
    }

    @Transactional
    public void update(Editorial editorial2) {
        Editorial editorial = editorialRepository.findById(editorial2.getId()).get();

        editorial.setName(editorial2.getName());

        editorialRepository.save(editorial);
    }

    @Transactional
    public void deleteById(Editorial editorial2) {
        Editorial editorial = editorialRepository.findById(editorial2.getId()).get();
        editorialRepository.delete(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> getAll() {
        return editorialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Editorial getById(Long id) {
        return editorialRepository.findById(id).get();
    }

}
