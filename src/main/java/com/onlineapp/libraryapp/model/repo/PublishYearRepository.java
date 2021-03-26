package com.onlineapp.libraryapp.model.repo;

import com.onlineapp.libraryapp.model.PublishYear;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishYearRepository extends CrudRepository<PublishYear, Integer> {


    @Query(value="SELECT * from publish_years WHERE publish_years.year=:year",nativeQuery=true)
    List<String> checkForDuplicate(@Param("year") int year);

    default int getIdPublishByYear(Integer year){
        for (PublishYear publishYear: this.findAll()){
            if(publishYear.getYear()==year){
                return publishYear.getId();
            }
        }
        return -1;
    }

}
