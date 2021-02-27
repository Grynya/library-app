package com.onlineApp.libraryApp.model.repo;

import com.onlineApp.libraryApp.model.PublishYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishYearRepository extends CrudRepository<PublishYear, Integer> {


     default int getIdPublishByYear(String year){
        for (PublishYear publishYear: this.findAll()){
            if(publishYear.getYear()==Integer.parseInt(year)){
                return publishYear.getId();
            }
        }
        return 0;
    }

}
