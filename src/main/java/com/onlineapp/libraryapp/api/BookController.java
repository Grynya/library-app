package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.model.request.AddBookRequest;
import com.onlineapp.libraryapp.model.request.UpdateBookRequest;
import com.onlineapp.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/addElements/books")
    public String booksInAddElements(Model model) {
        model.addAttribute("books", bookService.books());
        return "addElements";
    }

    @GetMapping("/modifyRemoveElements/books")
    public String booksInModifyRemoveElements(Model model) {
        model.addAttribute("books", bookService.books());
        return "modifyRemoveElements";
    }

    @GetMapping("/listsOfElements/books")
    public String booksListsOfElements(Model model) {
        model.addAttribute("books", bookService.books());
        return "listsOfElements";
    }

    @PostMapping("/addElements/addBook")
    public String addBook(Model model, AddBookRequest addBookRequest) {
        model.addAttribute("addBookResult", bookService.addBook(addBookRequest));
        return "addElements";
    }

    @PostMapping("/modifyRemoveElements/removeBook")
    public String removeBook(Model model, @RequestParam String name, @RequestParam String isbn, @RequestParam String lang) {
        model.addAttribute("removeBookResult", bookService.removeBook(name, isbn, lang));
        return "modifyRemoveElements";
    }

    @PostMapping("/modifyRemoveElements/updateBook")
    public String updateCover(Model model, UpdateBookRequest updateBookRequest) {
        model.addAttribute("updateBookResult", bookService.updateBook(updateBookRequest));
        return "modifyRemoveElements";
    }
}
