
package edu.egg.library.controller;

import edu.egg.library.entity.Editorial;
import edu.egg.library.service.EditorialService;
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
@RequestMapping("/editorials")
public class EditorialController {
    
    
    private final EditorialService editorialService;
    
    @Autowired
    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }
    
    @GetMapping("/get-all")
    public ModelAndView getEditorials(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("table-editorial");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(inputFlashMap!=null) mav.addObject("success", inputFlashMap.get("success"));
        
        mav.addObject("editorials", editorialService.getAll());
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form")
    public ModelAndView getFormEditorial(){
        ModelAndView mav = new ModelAndView("form-editorial");
        mav.addObject("editorial", new Editorial());
        mav.addObject("action", "create");
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id){  
        ModelAndView mav = new ModelAndView("form-editorial");
        mav.addObject("editorial", editorialService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RedirectView createEditorial(Editorial editorial, RedirectAttributes attributes){
        editorialService.create(editorial);
        attributes.addFlashAttribute("success", "La operación se realizó con éxito!");
        return new RedirectView("/editorials/get-all");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public RedirectView updateEditorial(Editorial editorial, RedirectAttributes attributes){
        editorialService.update(editorial);
        attributes.addFlashAttribute("success", "La operación se realizó con éxito!");
        return new RedirectView("/editorials/get-all");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public RedirectView deleteEditorial(Editorial editorial){
        editorialService.deleteById(editorial);
        return new RedirectView("/editorials/get-all");
    }
}
