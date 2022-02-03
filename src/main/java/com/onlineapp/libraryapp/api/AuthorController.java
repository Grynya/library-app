package com.onlineapp.libraryapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineapp.libraryapp.model.Author;
import com.onlineapp.libraryapp.model.request.AddAuthorRequest;
import com.onlineapp.libraryapp.model.request.RemoveAuthorRequest;
import com.onlineapp.libraryapp.model.request.UpdateAuthorRequest;
import com.onlineapp.libraryapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public Iterable<Author> authors() {
        return authorService.authors();
    }

    @PostMapping("/author")
    public String addAuthor(AddAuthorRequest authorRequest) {
        return authorService.addAuthor(authorRequest);
    }

    @DeleteMapping( "/author")
    public String removeAuthor(RemoveAuthorRequest removeAuthorRequest) {
        return authorService.removeAuthor(removeAuthorRequest);
    }

    @PutMapping("/author")
    public String updateAuthor(UpdateAuthorRequest updateAuthorRequest) {
        return authorService.updateAuthor(updateAuthorRequest);
    }

}