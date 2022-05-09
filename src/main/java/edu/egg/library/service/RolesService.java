
package edu.egg.library.service;

import edu.egg.library.entity.Roles;
import edu.egg.library.repository.RolesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolesService {
    
    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }
    
    @Transactional
    public void create(Roles role2){
        if(rolesRepository.existsByName(role2.getName()))
            throw new IllegalArgumentException();
        
        Roles role = new Roles();
        role.setName(role2.getName());
        rolesRepository.save(role);
    }
    
    @Transactional
    public List<Roles> getAll(){
        return rolesRepository.findAll();
    }
}
