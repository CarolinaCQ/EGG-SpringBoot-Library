
package edu.egg.library.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController implements ErrorController {
    
    @GetMapping("/error")
    public ModelAndView error(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("error");
        Integer status = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message;
        
        switch(status){
            case 403:
                message = "No tiene permisos de acceso!";
                break;
            case 404:
                message = "La url a la que desea acceder no existe!";
                break;
            case 500:
                message = "Error del servidor!";
                break;
            default:
                message = "Error inesperado!";
                
        }
        
        mav.addObject("message", message);
        mav.addObject("status", status);
        return mav;
    }
}
