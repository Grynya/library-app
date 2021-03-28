package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoverController {

    @Autowired
    private final CoverService coverService;

    public CoverController(CoverService coverService) {
        this.coverService = coverService;
    }

    @GetMapping("/addElements/covers")
    public String coversInAddElements(Model model) {
        model.addAttribute("covers", coverService.covers());
        return "addElements";
    }

    @GetMapping("/modifyRemoveElements/covers")
    public String coversInModifyRemoveElements(Model model) {
        model.addAttribute("covers", coverService.covers());
        return "modifyRemoveElements";
    }

    @GetMapping("/listsOfElements/covers")
    public String coversInListsOfElements(Model model) {
        model.addAttribute("covers", coverService.covers());
        return "listsOfElements";
    }

    @PostMapping("/addElements/addCover")
    public String addCover(Model model, @RequestParam String cover, @RequestParam String lang) {
        model.addAttribute("addCoverResult", coverService.addCover(cover, lang));
        return "addElements";
    }

    @PostMapping("/modifyRemoveElements/removeCover")
    public String removeCover(Model model, @RequestParam String cover, @RequestParam String lang) {
        model.addAttribute("removeCoverResult", coverService.removeCover(cover, lang));
        return "modifyRemoveElements";
    }

    @PostMapping("/modifyRemoveElements/updateCover")
    public String updateCover(Model model, @RequestParam String oldCover, @RequestParam String newCover, @RequestParam String lang) {
        model.addAttribute("updateCoverResult", coverService.updateCover(oldCover, newCover, lang));
        return "modifyRemoveElements";
    }
}

