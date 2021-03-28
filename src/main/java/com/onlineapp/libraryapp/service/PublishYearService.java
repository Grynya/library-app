package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.config.ResultConfig;
import com.onlineapp.libraryapp.model.Book;
import com.onlineapp.libraryapp.model.PublishYear;
import com.onlineapp.libraryapp.model.repo.BookRepository;
import com.onlineapp.libraryapp.model.repo.PublishYearRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PublishYearService {

    @Autowired
    private final PublishYearRepository publishYearRepository;

    @Autowired
    private final ResultConfig resultConfig;

    public PublishYearService(PublishYearRepository publishYearRepository, ResultConfig resultConfig) {
        this.publishYearRepository = publishYearRepository;
        this.resultConfig = resultConfig;
    }

    public Iterable<PublishYear> publishYears() {
        return publishYearRepository.findAll();
    }

    public String addPublishYear(String year, String lang) {
        try {
            publishYearRepository.save(new PublishYear(Integer.parseInt(year.replaceAll("\\s+", ""))));
            return resultConfig.result().getAddElementResultSuccessAdded().get(lang);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            return resultConfig.result().getAddElementResultNotAdded().get(lang);
        }
    }

    @Autowired
    BookRepository bookRepository;

    public String removePublishYear(String year, String lang) {
        for (Book b : bookRepository.findAll()) {
            if (b.getPublishYear() == publishYearRepository.getIdPublishByYear(Integer.parseInt(year))) {
                return resultConfig.result().getHasReferences().get(lang);
            }
        }
        if (publishYearRepository.findById(publishYearRepository.getIdPublishByYear(Integer.parseInt(year))).isPresent()) {
            publishYearRepository.deleteById(publishYearRepository.getIdPublishByYear(Integer.parseInt(year)));
            return resultConfig.result().getRemoveElementResultSuccessRemoved().get(lang);
        }
        return resultConfig.result().getRemoveElementResultNotRemoved().get(lang);
    }

    public String updatePublishYear(String oldPublishYear, String newPublishYear, String lang) {
        if (publishYearRepository.
                findById(publishYearRepository.getIdPublishByYear(Integer.parseInt(oldPublishYear))).isPresent()) {

            PublishYear publishYear = publishYearRepository.
                    findById(publishYearRepository.getIdPublishByYear(Integer.parseInt(oldPublishYear))).get();

            publishYear.setYear(Integer.parseInt(newPublishYear));
            publishYearRepository.save(publishYear);
            return resultConfig.result().getModifyElementResultSuccessModified().get(lang);
        }
        return resultConfig.result().getModifyElementResultNotModified().get(lang);
    }
}