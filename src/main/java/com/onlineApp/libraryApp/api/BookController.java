package com.onlineApp.libraryApp.api;

import com.onlineApp.libraryApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/allBooks")
    public String allBooks(Model model) {
        ArrayList<HashMap<String, String>> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "mainPage";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String patronymic, @RequestParam String name, @RequestParam String publishYear, @RequestParam int edition, @RequestParam String cover, @RequestParam String isbn){
        bookService.addBook(firstName, lastName, patronymic, name, publishYear, edition, cover, isbn);
        return "mainPage";
    }

}
