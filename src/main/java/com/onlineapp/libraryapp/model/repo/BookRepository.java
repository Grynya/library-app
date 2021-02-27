package com.onlineApp.libraryApp.model.repo;

import com.onlineApp.libraryApp.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

     default int getIdBookByName(String name){
        for (Book book: this.findAll()){
            if(book.getName().equals(name)){
                return book.getId();
            }
        }
        return 0;
    }
}
