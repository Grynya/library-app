package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.model.Cover;
import com.onlineapp.libraryapp.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoverController {

    @Autowired
    private final CoverService coverService;

    public CoverController(CoverService coverService) {
        this.coverService = coverService;
    }

    @GetMapping("/addElements/covers")
    public Iterable<Cover> coversInAddElements() {
        return coverService.covers();
    }

    @PostMapping("/cover")
    public String addCover(@RequestParam String cover, @RequestParam String lang) {
        return coverService.addCover(cover, lang);
    }

    @DeleteMapping("/cover")
    public String removeCover(@RequestParam String cover, @RequestParam String lang) {
        return coverService.removeCover(cover, lang);
    }

    @PutMapping("/cover")
    public String updateCover(@RequestParam String oldCover, @RequestParam String newCover, @RequestParam String lang) {
        return coverService.updateCover(oldCover, newCover, lang);
    }
}

