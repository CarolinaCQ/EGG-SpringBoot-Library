
package edu.egg.library.controller;

import edu.egg.library.entity.Book;
import edu.egg.library.service.AuthorService;
import edu.egg.library.service.BookService;
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
@RequestMapping("/books")
public class BookController {
    
    
    private final BookService bookService;
    private final AuthorService authorService;
    private final EditorialService editorialService;
    
    @Autowired
    public BookController(BookService bookService, AuthorService authorService, EditorialService editorialService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.editorialService = editorialService;
    }
    
    @GetMapping("/get-all")
    public ModelAndView getBooks(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("table-book");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(inputFlashMap!=null) mav.addObject("success", inputFlashMap.get("success"));
        
        mav.addObject("books", bookService.getAll());
        
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form")
    public ModelAndView getFormBook(){
        ModelAndView mav = new ModelAndView("form-book");
        mav.addObject("book", new Book());
        mav.addObject("authors", authorService.getAll());
        mav.addObject("editorials", editorialService.getAll());
        mav.addObject("action", "create");
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id){  
        ModelAndView mav = new ModelAndView("form-book");
        mav.addObject("book", bookService.getById(id));
        mav.addObject("authors", authorService.getAll());
        mav.addObject("editorials", editorialService.getAll());
        mav.addObject("action", "update");
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RedirectView createBook(Book book, RedirectAttributes attributes){
        bookService.create(book);
        attributes.addFlashAttribute("success", "La operación se realizó con éxito!");
        return new RedirectView("/books/get-all");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public RedirectView updateBook(Book book, RedirectAttributes attributes){
        bookService.update(book);
        attributes.addFlashAttribute("success", "La operación se realizó con éxito!");
        return new RedirectView("/books/get-all");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update-low/{id}")
    public ModelAndView updateLowBook(Book book){
        ModelAndView mav = new ModelAndView("redirect:/books/get-all");
        bookService.updateLow(book);
        return mav;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public ModelAndView deleteBook(Book book){
        ModelAndView mav = new ModelAndView("redirect:/books/get-all");
        bookService.deleteById(book);
        return mav;
    }
    
}
