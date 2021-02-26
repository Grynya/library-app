package com.onlineApp.libraryApp.api;

import com.onlineApp.libraryApp.model.PublishYear;
import com.onlineApp.libraryApp.services.PublishYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublishYearController {


    @Autowired
    PublishYearService publishYearService;

    @GetMapping("/allPublishYears")
    public String allPublishYears(Model model) {
        Iterable<PublishYear> publishYears = publishYearService.getAllPublishYears();
        model.addAttribute("publishYears", publishYears);
        return "mainPage";
    }


    @PostMapping("/addPublishYear")
    public String addPublishYears(@RequestParam String publishYear){
        publishYearService.addPublishYear(publishYear);
        return "mainPage";
    }
}
