package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public String authors(Model model) {
        model.addAttribute("authors", authorService.authors());
        return "addElements";
    }

    @GetMapping("/modifyRemoveElements/authors")
    public String authors2(Model model) {
        model.addAttribute("authors", authorService.authors());
        return "modifyRemoveElements";
    }
    @GetMapping("/listsOfElements/authors")
    public String authors3(Model model) {
        model.addAttribute("authors", authorService.authors());
        return "listsOfElements";
    }

    @PostMapping("/addElements/addAuthor")
    public String addAuthor(Model model, @RequestParam String firstName, @RequestParam String lastName,
                            @RequestParam String patronymic, @RequestParam String lang){
        model.addAttribute("addAuthorResult", authorService.addAuthor(firstName, lastName, patronymic, lang));
        System.out.println(lang);
        return "addElements";
    }

    @PostMapping("/modifyRemoveElements/removeAuthor")
    public String removeAuthor(Model model, @RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String patronymic, @RequestParam String lang){
        model.addAttribute("removeAuthorResult", authorService.removeAuthor(firstName, lastName, patronymic, lang));
        return "modifyRemoveElements";
    }

    @PostMapping("/modifyRemoveElements/updateAuthor")
    public String updateAuthor(Model model, @Param("oldFirstName") String oldFirstName, @Param("oldLastName") String oldLastName, @Param("oldPatronymic") String oldPatronymic,
                               @Param("newFirstName") String newLastName, @Param("newLastName") String newFirstName, @Param("newPatronymic") String newPatronymic, @RequestParam String lang){
        model.addAttribute("updateAuthorResult", authorService.updateAuthor(oldFirstName, oldLastName, oldPatronymic, newLastName, newFirstName, newPatronymic, lang));
        return "modifyRemoveElements";
    }

}