package com.onlineApp.libraryApp.api;

import com.onlineApp.libraryApp.model.Edition;
import com.onlineApp.libraryApp.services.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditionController {


    @Autowired
    EditionService editionService;

    @GetMapping("/allEditions")
    public String allCovers(Model model) {
        Iterable<Edition> editions = editionService.getAllEditions();
        model.addAttribute("editions", editions);
        return "mainPage";
    }


    @PostMapping("/addEdition")
    public String addEdition(@RequestParam int edition){
        editionService.addEdition(edition);
        return "mainPage";
    }
}
