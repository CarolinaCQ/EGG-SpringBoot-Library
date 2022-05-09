
package edu.egg.library.service;

import edu.egg.library.entity.User;
import edu.egg.library.repository.RolesRepository;
import edu.egg.library.repository.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.rolesRepository = rolesRepository;
    }
    
    @Transactional
    public void create(User user2){
        if (userRepository.existsByEmail(user2.getEmail())){
            throw new IllegalArgumentException();
        }
        
        User user = new User();
        
        user.setEmail(user2.getEmail());
        user.setPassword(encoder.encode(user2.getPassword()));
        user.setName(user2.getName());
        user.setSurname(user2.getSurname());
        
        if(user2.getRol() == null) {
            user.setRol(rolesRepository.findByName("USER").orElseThrow(()-> new IllegalArgumentException("")));
        }
           
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(email).orElseThrow(( )-> new UsernameNotFoundException(""));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRol().getName());
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singletonList(authority));
    }

}
