
package edu.egg.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
    
    @GetMapping
    public ModelAndView getPrincipal(){
        return new ModelAndView("index");
    }
}
