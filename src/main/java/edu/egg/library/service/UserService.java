
package edu.egg.library.service;

import edu.egg.library.entity.Roles;
import edu.egg.library.entity.User;
import edu.egg.library.repository.UserRepository;
import java.util.Collections;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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
        
        if(userRepository.findAll().isEmpty()){
            user.setRol(Roles.ADMIN);
        } else {
            user.setRol(Roles.USER);
        }
           
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(email).orElseThrow(( )-> new UsernameNotFoundException(""));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRol());
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        
        session.setAttribute("id", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRol());
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singletonList(authority));
    }

}
