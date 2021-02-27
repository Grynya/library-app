package com.onlineApp.libraryApp.model.repo;

import com.onlineApp.libraryApp.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer>{


    @Query(value="SELECT authors.first_name, authors.last_name, authors.patronymic \n" +
            "FROM authors\n" +
            "INNER JOIN authors_books ON authors.id = (SELECT id_author FROM authors_books WHERE id_book=:idBook);",nativeQuery=true)
    List<String> getAuthorByIdBook(@Param("idBook") Integer idBook);


    default int getIdAuthorByLastName(String lastName){
        for (Author author: this.findAll()){
            if(author.getLastName().equals(lastName)){
                return author.getId();
            }
        }
        return 0;
    }


}
