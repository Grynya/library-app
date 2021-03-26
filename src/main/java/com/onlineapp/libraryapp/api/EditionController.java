package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/addElements/editions")
    public String editions(Model model) {
        model.addAttribute("editions", editionService.editions());
        return "addElements";
    }
    @GetMapping("/modifyRemoveElements/editions")
    public String editions2(Model model) {
        model.addAttribute("editions", editionService.editions());
        return "modifyRemoveElements";
    }
    @GetMapping("/listsOfElements/editions")
    public String editions3(Model model) {
        model.addAttribute("editions", editionService.editions());
        return "listsOfElements";
    }
    @PostMapping("/addElements/addEdition")
    public String addEdition(Model model, @RequestParam String edition, @RequestParam String lang){
        model.addAttribute("addEditionResult", editionService.addEdition(edition, lang));
        return "addElements";
    }

    @PostMapping("/modifyRemoveElements/removeEdition")
    public String removeEdition(Model model, @RequestParam String edition, @RequestParam String lang){
        model.addAttribute("removeEditionResult", editionService.removeEdition(edition, lang));
        return "modifyRemoveElements";
    }

    @PostMapping("/modifyRemoveElements/updateEdition")
    public String updateEdition(Model model, @RequestParam String oldEdition, @RequestParam String newEdition, @RequestParam String lang){
        model.addAttribute("updateEditionResult", editionService.updateEdition(oldEdition, newEdition, lang));
        return "modifyRemoveElements";
    }

}
