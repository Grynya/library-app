package com.onlineApp.libraryApp.services;

import com.onlineApp.libraryApp.model.PublishYear;
import com.onlineApp.libraryApp.model.repo.PublishYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishYearService {

    @Autowired
    PublishYearRepository publishYearRepository;

    public Iterable<PublishYear> getAllPublishYears() {

        Iterable<PublishYear> publishYears = publishYearRepository.findAll();
        return publishYears;
    }

    public void addPublishYear(String year){
        publishYearRepository.save(new PublishYear(Integer.parseInt(year)));
    }

}
