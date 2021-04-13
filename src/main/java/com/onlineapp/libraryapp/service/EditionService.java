package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.config.ResultConfig;
import com.onlineapp.libraryapp.model.Book;
import com.onlineapp.libraryapp.model.Edition;
import com.onlineapp.libraryapp.model.repo.BookRepository;
import com.onlineapp.libraryapp.model.repo.EditionRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EditionService {

    @Autowired
    private final EditionRepository editionRepository;

    @Autowired
    private final ResultConfig resultConfig;

    public EditionService(EditionRepository editionRepository, ResultConfig resultConfig) {
        this.editionRepository = editionRepository;
        this.resultConfig = resultConfig;
    }


    public Iterable<Edition> editions() {

        return editionRepository.findAll();
    }

    public String addEdition(String edition, String lang) {
        try {
            editionRepository.save(new Edition(Integer.parseInt(edition.replaceAll("\\s+", ""))));
            return resultConfig.result().getAddElementResultSuccessAdded().get(lang);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            return resultConfig.result().getAddElementResultNotAdded().get(lang);
        }
    }

    @Autowired
    BookRepository bookRepository;

    public String removeEdition(String edition, String lang) {
        for (Book b : bookRepository.findAll()) {
            if (b.getEdition() == editionRepository.getIdEditionByValue(Integer.parseInt(edition))) {
                return resultConfig.result().getHasReferences().get(lang);
            }
        }
        if (editionRepository.findById(editionRepository.getIdEditionByValue(Integer.parseInt(edition))).isPresent()) {

            editionRepository.deleteById(editionRepository.getIdEditionByValue(Integer.parseInt(edition)));
            return resultConfig.result().getRemoveElementResultSuccessRemoved().get(lang);
        }
        return resultConfig.result().getRemoveElementResultNotRemoved().get(lang);
    }

    public String updateEdition(String oldEdition, String newEdition, String lang) {
        if (editionRepository.findById(editionRepository.getIdEditionByValue(Integer.parseInt(oldEdition))).isPresent()) {
            Edition edition = editionRepository.findById(editionRepository.getIdEditionByValue(Integer.parseInt(oldEdition))).get();
            edition.setValue(Integer.parseInt(newEdition));
            editionRepository.save(edition);
            return resultConfig.result().getModifyElementResultSuccessModified().get(lang);
        }
        return resultConfig.result().getModifyElementResultNotModified().get(lang);
    }
}