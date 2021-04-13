package com.onlineapp.libraryapp.model.repo;

import com.onlineapp.libraryapp.model.AuthorBook;
import com.onlineapp.libraryapp.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookRepository extends CrudRepository<AuthorBook, Integer> {

    default int getIdAuthorBookByBook(Integer idBook) {
        for (AuthorBook authorBook : this.findAll()) {
            if (authorBook.getIdBook() == idBook) {
                return authorBook.getId();
            }
        }
        return -1;
    }

}


