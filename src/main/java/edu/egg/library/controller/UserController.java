
package edu.egg.library.controller;

import edu.egg.library.entity.User;
import edu.egg.library.service.UserService;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/login")
    public ModelAndView formLogin(@RequestParam(required=false) String error, @RequestParam(required=false) String logout, Principal principal){
        ModelAndView mav = new ModelAndView("form-login");
        
        if(error!=null) mav.addObject("error", "Email o Contraseña incorrecta");
        if(logout!=null) mav.addObject("logout", "Ha salido con éxito!");
        if(principal!=null) mav.setViewName("redirect:/inicio");
        
        return mav;
    }
    
    @GetMapping("/sign-up")
    public ModelAndView formSingUp(HttpServletRequest request, Principal principal){
        ModelAndView mav = new ModelAndView("form-sign-up");
        Map<String,?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(principal!=null) mav.setViewName("redirect:/inicio");
        
        if(inputFlashMap!=null){
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("user", inputFlashMap.get("user"));
        } else{
            mav.addObject("user", new User());
        }
        
        return mav;
    }
    
    @PostMapping("/register")
    public RedirectView create(User user, RedirectAttributes attributes){
        
        RedirectView redirectView = new RedirectView("/inicio");
        
        try {
            userService.create(user);
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("user", user);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirectView.setUrl("/users/sign-up");
        }

        return redirectView;
    }
}
