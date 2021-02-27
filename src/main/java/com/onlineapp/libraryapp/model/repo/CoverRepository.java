package com.onlineApp.libraryApp.model.repo;

import com.onlineApp.libraryApp.model.Cover;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverRepository extends CrudRepository<Cover, Integer>{


     default int getIdCoverByName(String name){
        for (Cover cover: this.findAll()){
            if(cover.getName().equals(name)){
                return cover.getId();
            }
        }
        return 0;
    }

}
