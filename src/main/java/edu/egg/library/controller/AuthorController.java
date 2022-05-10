
package edu.egg.library.controller;

import edu.egg.library.entity.Author;
import edu.egg.library.service.AuthorService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    
    
    private final AuthorService authorService;
    
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    
    @GetMapping("/get-all")
    public ModelAndView getAuthors(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("table-author");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(inputFlashMap!=null) mav.addObject("success", inputFlashMap.get("success"));
        
        mav.addObject("authors", authorService.getAll());
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form")
    public ModelAndView getFormAuthor(){
        ModelAndView mav = new ModelAndView("form-author");
        mav.addObject("author", new Author());
        mav.addObject("action", "create");
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id){  
        ModelAndView mav = new ModelAndView("form-author");
        mav.addObject("author", authorService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RedirectView createAuthor(Author author, RedirectAttributes attributes){
        authorService.create(author);
        attributes.addFlashAttribute("success", "La operación se realizó con éxito!");
        return new RedirectView("/authors/get-all");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public RedirectView updateAuthor(Author author, RedirectAttributes attributes){
        authorService.update(author);
        attributes.addFlashAttribute("success", "La operación se realizó con éxito!");
        return new RedirectView("/authors/get-all");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update-low/{id}")
    public RedirectView updateLow(Author author){
        authorService.updateLow(author);
        return new RedirectView("/authors/get-all");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public RedirectView deleteAuthor(Author author){
        authorService.deleteById(author);
        return new RedirectView("/authors/get-all");
    }
    
}

