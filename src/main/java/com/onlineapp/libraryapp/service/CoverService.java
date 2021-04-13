package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.config.ResultConfig;
import com.onlineapp.libraryapp.model.Book;
import com.onlineapp.libraryapp.model.Cover;
import com.onlineapp.libraryapp.model.repo.BookRepository;
import com.onlineapp.libraryapp.model.repo.CoverRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CoverService {

    @Autowired
    private final CoverRepository coverRepository;

    @Autowired
    private final ResultConfig resultConfig;

    public CoverService(CoverRepository coverRepository, ResultConfig resultConfig) {
        this.coverRepository = coverRepository;
        this.resultConfig = resultConfig;
    }

    public Iterable<Cover> covers() {
        return coverRepository.findAll();
    }

    public String addCover(String cover, String lang) {
        try {
            coverRepository.save(new Cover(cover.replaceAll("\\s+", "")));
            return resultConfig.result().getAddElementResultSuccessAdded().get(lang);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            return resultConfig.result().getAddElementResultNotAdded().get(lang);

        }
    }

    @Autowired
    BookRepository bookRepository;

    public String removeCover(String cover, String lang) {
        for (Book b : bookRepository.findAll()) {
            if (b.getCover() == coverRepository.getIdCoverByName(cover)) {
                return resultConfig.result().getHasReferences().get(lang);
            }
        }
        if (coverRepository.findById(coverRepository.getIdCoverByName(cover)).isPresent()) {
            coverRepository.deleteById(coverRepository.getIdCoverByName(cover));
            return resultConfig.result().getRemoveElementResultSuccessRemoved().get(lang);
        }
        return resultConfig.result().getRemoveElementResultNotRemoved().get(lang);
    }

    public String updateCover(String oldName, String newName, String lang) {
        if (coverRepository.findById(coverRepository.getIdCoverByName(oldName)).isPresent()) {
            Cover cover = coverRepository.findById(coverRepository.getIdCoverByName(oldName)).get();
            cover.setName(newName);
            coverRepository.save(cover);
            return resultConfig.result().getModifyElementResultSuccessModified().get(lang);
        }
        return resultConfig.result().getModifyElementResultNotModified().get(lang);
    }

}