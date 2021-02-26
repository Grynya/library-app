package com.onlineApp.libraryApp.services;

import com.onlineApp.libraryApp.model.Edition;
import com.onlineApp.libraryApp.model.repo.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditionService {

    @Autowired
    EditionRepository editionRepository;


    public Iterable<Edition> getAllEditions() {

        Iterable<Edition> editions = editionRepository.findAll();
        return editions;
    }

    public void addEdition(int edition){
        editionRepository.save(new Edition(edition));
    }

}

