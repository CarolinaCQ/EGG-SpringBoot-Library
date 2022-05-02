
package edu.egg.library.repository;

import edu.egg.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository< Book, Long > {
    
}
