package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/addElements/books")
    public String books(Model model) {
        model.addAttribute("books", bookService.books());
        return "addElements";
    }
    @GetMapping("/modifyRemoveElements/books")
    public String books2(Model model) {
        model.addAttribute("books", bookService.books());
        return "modifyRemoveElements";
    }
    @GetMapping("/listsOfElements/books")
    public String books3(Model model) {
        model.addAttribute("books", bookService.books());
        return "listsOfElements";
    }

    @PostMapping("/addElements/addBook")
    public String addBook(Model model,
                          @RequestParam String firstName, @RequestParam String lastName, @RequestParam String patronymic,
                          @RequestParam String name, @RequestParam String publishYear, @RequestParam String edition,
                          @RequestParam String cover, @RequestParam String isbn, @RequestParam String lang){
        model.addAttribute("addBookResult", bookService.addBook(firstName, lastName, patronymic, name, publishYear, edition, cover, isbn, lang));
        return "addElements";
    }

    @PostMapping("/modifyRemoveElements/removeBook")
    public String removeBook(Model model, @RequestParam String name, @RequestParam String isbn, @RequestParam String lang){
        model.addAttribute("removeBookResult", bookService.removeBook(name, isbn, lang));
        return "modifyRemoveElements";
    }

    @PostMapping("/modifyRemoveElements/updateBook")
    public String updateCover(Model model, @RequestParam String oldName, @RequestParam String oldIsbn,
                              @RequestParam String newFirstName, @RequestParam String newLastName, @RequestParam String newPatronymic,
                              @RequestParam String newName, @RequestParam String newPublishYear, @RequestParam String newEdition,
                              @RequestParam String newCover, @RequestParam String newIsbn, @RequestParam String lang){
        model.addAttribute("updateBookResult", bookService.updateBook(oldName, oldIsbn,  newFirstName, newLastName, newPatronymic,
               newName, Integer.parseInt(newPublishYear), Integer.parseInt(newEdition), newCover,  newIsbn, lang));
        return "modifyRemoveElements";
    }
}
