package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.service.PublishYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublishYearController {

    @Autowired
    private final PublishYearService publishYearService;

    public PublishYearController(PublishYearService publishYearService) {
        this.publishYearService = publishYearService;
    }

    @GetMapping("/addElements/publishYears")
    public String publishYearsInAddElements(Model model) {
        model.addAttribute("publishYears", publishYearService.publishYears());
        return "addElements";
    }

    @GetMapping("/modifyRemoveElements/publishYears")
    public String publishYearsInModifyRemoveElements(Model model) {
        model.addAttribute("publishYears", publishYearService.publishYears());
        return "modifyRemoveElements";
    }

    @GetMapping("/listsOfElements/publishYears")
    public String publishYearsInListsOfElements(Model model) {
        model.addAttribute("publishYears", publishYearService.publishYears());
        return "listsOfElements";
    }

    @PostMapping("/addElements/addPublishYear")
    public String addPublishYears(Model model, @RequestParam String publishYear, @RequestParam String lang) {
        model.addAttribute("addYearResult", publishYearService.addPublishYear(publishYear, lang));
        return "addElements";
    }

    @PostMapping("/modifyRemoveElements/removePublishYear")
    public String removePublishYear(Model model, @RequestParam String publishYear, @RequestParam String lang) {
        model.addAttribute("removeYearResult", publishYearService.removePublishYear(publishYear, lang));
        return "modifyRemoveElements";
    }

    @PostMapping("/modifyRemoveElements/updatePublishYear")
    public String updatePublishYear(Model model, @RequestParam String oldPublishYear, @RequestParam String newPublishYear, @RequestParam String lang) {
        model.addAttribute("updatePublishYearResult", publishYearService.updatePublishYear(oldPublishYear, newPublishYear, lang));
        return "modifyRemoveElements";
    }
}