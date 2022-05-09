
package edu.egg.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inicio")
public class PrincipalController {
    
    @GetMapping
    public ModelAndView getPrincipal(){
        return new ModelAndView("index");
    }
}
