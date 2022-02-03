package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.model.Edition;
import com.onlineapp.libraryapp.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditionController {


    @Autowired
    private final EditionService editionService;

    public EditionController(EditionService editionService) {
        this.editionService = editionService;
    }

    @GetMapping("/editions")
    public Iterable<Edition> editionsInListsOfElements() {
        return editionService.editions();
    }

    @PostMapping("/edition")
    public String addEdition(@RequestParam String edition, @RequestParam String lang) {
        return editionService.addEdition(edition, lang);
    }

    @DeleteMapping("/edition")
    public String removeEdition(@RequestParam String edition, @RequestParam String lang) {
        return editionService.removeEdition(edition, lang);
    }

    @PutMapping("/edition")
    public String updateEdition(@RequestParam String oldEdition, @RequestParam String newEdition,
                                @RequestParam String lang) {
        return editionService.updateEdition(oldEdition, newEdition, lang);
    }

}
