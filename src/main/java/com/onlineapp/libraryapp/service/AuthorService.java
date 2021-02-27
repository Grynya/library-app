package com.onlineApp.libraryApp.services;

import com.onlineApp.libraryApp.model.Author;
import com.onlineApp.libraryApp.model.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;


    public Iterable<Author> getAllAuthors() {

        Iterable<Author> authors = authorRepository.findAll();
        return authors;
    }

    public void addAuthor(String firstName, String lastName, String patronymic){
        Author author = new Author(firstName, lastName, patronymic);

        authorRepository.save(author);
    }

}
