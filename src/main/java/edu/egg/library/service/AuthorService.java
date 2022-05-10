
package edu.egg.library.service;

import edu.egg.library.entity.Author;
import edu.egg.library.repository.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {
    
    
    private final AuthorRepository authorRepository;
    
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    @Transactional
    public void create(Author author2){
        Author author = new Author();
        
        author.setName(author2.getName());
        author.setSurname(author2.getSurname());
        author.setLow(false);
        
        authorRepository.save(author);
    }

    @Transactional
    public void update(Author author2){
        Author author = authorRepository.findById(author2.getId()).get();
        
        author.setName(author2.getName());
        author.setSurname(author2.getSurname());
        
        authorRepository.save(author);
    }
    
    @Transactional
    public void updateLow(Author author2){
        Author author = authorRepository.findById(author2.getId()).get();
        
        author.setLow(false);
        
        authorRepository.save(author);
    }
    
    @Transactional
    public void deleteById(Author author2){
        Author author = authorRepository.findById(author2.getId()).get();
        authorRepository.delete(author);
    }
    
    @Transactional(readOnly = true)
    public List<Author> getAll(){
        return authorRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Author getById(Long id) {
        return authorRepository.findById(id).get();
    }
    
}

