package com.onlineApp.libraryApp.api;

import com.onlineApp.libraryApp.model.Author;
import com.onlineApp.libraryApp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {

   /* @RequestMapping("/")
    public String greeting() {
        return "greeting";
    }*/


    @Autowired
    AuthorService authorService;

    @GetMapping("/allAuthors")
    public String allAuthors(Model model) {
        Iterable<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "mainPage";
    }


    @PostMapping("/addAuthor")
    public String addAuthor(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String patronymic){
    authorService.addAuthor(firstName, lastName, patronymic);
    return "mainPage";
    }
}
