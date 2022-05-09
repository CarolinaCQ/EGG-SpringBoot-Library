
package edu.egg.library.repository;

import edu.egg.library.entity.Roles;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    
    boolean existsByName(String name);
    
    Optional<Roles> findByName(String name);
}
