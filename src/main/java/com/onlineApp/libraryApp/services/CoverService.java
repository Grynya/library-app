package com.onlineApp.libraryApp.services;

import com.onlineApp.libraryApp.model.Cover;
import com.onlineApp.libraryApp.model.repo.CoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverService {

    @Autowired
    CoverRepository coverRepository;

    public Iterable<Cover> getAllCovers() {

        Iterable<Cover> covers = coverRepository.findAll();
        return covers;
    }

    public void addCover(String cover){
        coverRepository.save(new Cover(cover));
    }

}
