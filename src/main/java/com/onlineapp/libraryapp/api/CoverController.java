package com.onlineApp.libraryApp.api;

import com.onlineApp.libraryApp.model.Cover;
import com.onlineApp.libraryApp.services.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoverController {

    @Autowired
    CoverService coverService;

    @GetMapping("/allCovers")
    public String allCovers(Model model) {
        Iterable<Cover> covers = coverService.getAllCovers();
        model.addAttribute("covers", covers);
        return "mainPage";
    }


    @PostMapping("/addCover")
    public String addAuthor(@RequestParam String cover){
        coverService.addCover(cover);
        return "mainPage";
    }

}
