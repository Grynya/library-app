package com.onlineApp.libraryApp.model.repo;

import com.onlineApp.libraryApp.model.Edition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionRepository extends CrudRepository<Edition, Integer> {


     default int getIdEditionByValue(Integer value){
        for (Edition edition: this.findAll()){
            if(edition.getValue()==value){
                return edition.getId();
            }
        }
        return 0;
    }


}
