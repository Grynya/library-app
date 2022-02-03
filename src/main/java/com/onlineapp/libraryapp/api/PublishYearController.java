package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.model.PublishYear;
import com.onlineapp.libraryapp.service.PublishYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/publishYears")
    public Iterable<PublishYear> publishYears() {
        return publishYearService.publishYears();
    }

    @PostMapping("/publishYear")
    public String addPublishYears(@RequestParam String publishYear, @RequestParam String lang) {
        return publishYearService.addPublishYear(publishYear, lang);
    }

    @DeleteMapping("/publishYear")
    public String removePublishYear(@RequestParam String publishYear, @RequestParam String lang) {
        return publishYearService.removePublishYear(publishYear, lang);
    }

    @PutMapping("/publishYear")
    public String updatePublishYear(@RequestParam String oldPublishYear, @RequestParam String newPublishYear,
                                    @RequestParam String lang) {
        return publishYearService.updatePublishYear(oldPublishYear, newPublishYear, lang);
    }
}