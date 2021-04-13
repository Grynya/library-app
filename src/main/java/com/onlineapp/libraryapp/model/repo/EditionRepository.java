package com.onlineapp.libraryapp.model.repo;

import com.onlineapp.libraryapp.model.Edition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditionRepository extends CrudRepository<Edition, Integer> {


    @Query(value = "SELECT * from editions WHERE editions.value=:value", nativeQuery = true)
    List<String> checkForDuplicate(@Param("value") int value);

    default Integer getIdEditionByValue(Integer value) {
        for (Edition edition : this.findAll()) {
            if (edition.getValue() == value) {
                return edition.getId();
            }
        }
        return -1;
    }

}