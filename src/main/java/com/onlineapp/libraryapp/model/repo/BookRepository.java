package com.onlineapp.libraryapp.model.repo;

import com.onlineapp.libraryapp.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

    @Query(value="SELECT * from books WHERE books.name=:name or books.isbn=:isbn",nativeQuery=true)
    List<String> checkForDuplicate(@Param("name") String name, @Param("isbn") String isbn);

    default int getIdBookByNameAndIsbn(String name, String isbn) {
        for (Book book : this.findAll()) {
            if (book.getName().equals(name) && book.getIsbn().equals(isbn)) {
                return book.getId();
            }
        }
        return -1;
    }
}

