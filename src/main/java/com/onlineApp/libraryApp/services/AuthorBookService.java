package com.onlineApp.libraryApp.services;

import com.onlineApp.libraryApp.model.AuthorBook;
import com.onlineApp.libraryApp.model.repo.AuthorBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorBookService {
    @Autowired
    AuthorBookRepository authorBookRepository;

    public Iterable<AuthorBook> getAllAuthorsBooks() {

        Iterable<AuthorBook> authorsBooks = authorBookRepository.findAll();
        return authorsBooks;
    }

}
