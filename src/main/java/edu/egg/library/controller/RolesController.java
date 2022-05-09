
package edu.egg.library.controller;

import edu.egg.library.entity.Roles;
import edu.egg.library.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/roles")
public class RolesController {
    
    private final RolesService rolesService;
    
    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form")
    public ModelAndView getForm(){
        ModelAndView mav = new ModelAndView("role-form");
        mav.addObject("role", new Roles());
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RedirectView create(Roles role){
        rolesService.create(role);
        return new RedirectView("/inicio");
    }
}
