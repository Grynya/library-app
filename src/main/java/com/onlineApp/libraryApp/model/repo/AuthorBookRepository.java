package com.onlineApp.libraryApp.model.repo;

import com.onlineApp.libraryApp.model.Author;
import com.onlineApp.libraryApp.model.AuthorBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookRepository extends CrudRepository< AuthorBook, Integer>{
}
