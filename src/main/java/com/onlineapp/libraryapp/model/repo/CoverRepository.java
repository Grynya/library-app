package com.onlineapp.libraryapp.model.repo;

import com.onlineapp.libraryapp.model.Cover;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoverRepository extends CrudRepository<Cover, Integer> {

    @Query(value = "SELECT * from covers WHERE covers.name=:name", nativeQuery = true)
    List<String> checkForDuplicate(@Param("name") String name);

    default int getIdCoverByName(String name) {
        for (Cover cover : this.findAll()) {
            if (cover.getName().equals(name)) {
                return cover.getId();
            }
        }
        return -1;
    }
}

