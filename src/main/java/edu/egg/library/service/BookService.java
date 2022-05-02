
package edu.egg.library.service;

import edu.egg.library.entity.Book;
import edu.egg.library.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Transactional
    public void create(Book book2){
        Book book = new Book();
        
        book.setIsbn(book2.getIsbn());
        book.setTitle(book2.getTitle());
        book.setYear(book2.getYear());
        book.setCopies(book2.getCopies());
        book.setBorrowedCopies(book2.getBorrowedCopies());
        book.setRemainingCopies(book2.getRemainingCopies());
        book.setLow(false);
        book.setAuthor(book2.getAuthor());
        book.setEditorial(book2.getEditorial());
        
        bookRepository.save(book);
    }

    @Transactional
    public void update(Book book2){
        Book book = bookRepository.findById(book2.getId()).get();
        
        book.setIsbn(book2.getIsbn());
        book.setTitle(book2.getTitle());
        book.setYear(book2.getYear());
        book.setCopies(book2.getCopies());
        book.setBorrowedCopies(book2.getBorrowedCopies());
        book.setRemainingCopies(book2.getRemainingCopies());
        book.setAuthor(book2.getAuthor());
        book.setEditorial(book2.getEditorial());
        
        bookRepository.save(book);
    }
    
    @Transactional
    public void deleteById(Book book2){
        Book book = bookRepository.findById(book2.getId()).get();
        bookRepository.delete(book);
    }
    
    @Transactional(readOnly = true)
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Book getById(Long id) {
        return bookRepository.findById(id).get();
    }
}
