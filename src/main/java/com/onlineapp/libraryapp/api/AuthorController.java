package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.model.request.AddAuthorRequest;
import com.onlineapp.libraryapp.model.request.RemoveAuthorRequest;
import com.onlineapp.libraryapp.model.request.UpdateAuthorRequest;
import com.onlineapp.libraryapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/addElements/authors")
    public String authorsInAddElements(Model model) {
        model.addAttribute("authors", authorService.authors());
        return "addElements";
    }

    @GetMapping("/modifyRemoveElements/authors")
    public String authorsInModifyRemoveElements(Model model) {
        model.addAttribute("authors", authorService.authors());
        return "modifyRemoveElements";
    }

    @GetMapping("/listsOfElements/authors")
    public String authorsInListsOfElements(Model model) {
        model.addAttribute("authors", authorService.authors());
        return "listsOfElements";
    }

    @PostMapping("/addElements/addAuthor")
    public String addAuthor(Model model, AddAuthorRequest authorRequest) {
        model.addAttribute("addAuthorResult", authorService.addAuthor(authorRequest));
        return "addElements";
    }

    @PostMapping("/modifyRemoveElements/removeAuthor")
    public String removeAuthor(Model model, RemoveAuthorRequest removeAuthorRequest) {
        model.addAttribute("removeAuthorResult", authorService.removeAuthor(removeAuthorRequest));
        return "modifyRemoveElements";
    }

    @PostMapping("/modifyRemoveElements/updateAuthor")
    public String updateAuthor(Model model, UpdateAuthorRequest updateAuthorRequest) {
        model.addAttribute("updateAuthorResult", authorService.updateAuthor(updateAuthorRequest));
        return "modifyRemoveElements";
    }

}