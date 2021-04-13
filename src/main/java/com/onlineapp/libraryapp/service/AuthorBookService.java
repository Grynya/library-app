package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.model.repo.AuthorBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorBookService {
    @Autowired
    private final AuthorBookRepository authorBookRepository;

    public AuthorBookService(AuthorBookRepository authorBookRepository) {
        this.authorBookRepository = authorBookRepository;
    }

}