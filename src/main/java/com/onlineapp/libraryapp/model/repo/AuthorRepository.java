package com.onlineapp.libraryapp.model.repo;

import com.onlineapp.libraryapp.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer>{


    @Query(value="SELECT authors.first_name, authors.last_name, authors.patronymic \n" +
            "            FROM authors \n" +
            "\tWHERE authors.id = (SELECT id_author FROM authors_books WHERE id_book=:idBook);",nativeQuery=true)
    List<String> getAuthorByIdBook(@Param("idBook") Integer idBook);

    @Query(value="SELECT FROM authors WHERE authors.first_name=:firstName and authors.last_name=:lastName and authors.patronymic=:patronymic",nativeQuery=true)
    List<String> checkForDuplicate(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("patronymic") String patronymic);

    default int getIdAuthor(String firstName, String lastName, String patronymic){
        for (Author author: this.findAll()){
            if(author.getFirstName().equals(firstName) && author.getLastName().equals(lastName) && author.getPatronymic().equals(patronymic)){
                return author.getId();
            }
        }
        return -1;
    }

}
