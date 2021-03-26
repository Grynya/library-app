package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.config.ResultConfig;
import com.onlineapp.libraryapp.model.Author;
import com.onlineapp.libraryapp.model.AuthorBook;
import com.onlineapp.libraryapp.model.Book;
import com.onlineapp.libraryapp.model.repo.AuthorBookRepository;
import com.onlineapp.libraryapp.model.repo.AuthorRepository;
import com.onlineapp.libraryapp.model.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final ResultConfig resultConfig;

    public AuthorService(AuthorRepository authorRepository, ResultConfig resultConfig) {
        this.authorRepository = authorRepository;
        this.resultConfig = resultConfig;
    }


    public Iterable<Author> authors() {
        System.out.println(resultConfig.result());

        return authorRepository.findAll();

    }

    public String addAuthor(String firstName, String lastName, String patronymic, String lang){
        if (authorRepository.checkForDuplicate(firstName.replaceAll("\\s+",""), lastName.replaceAll("\\s+",""), patronymic.replaceAll("\\s+","")).isEmpty()) {
            authorRepository.save(new Author(firstName, lastName, patronymic));
            return resultConfig.result().getAddElementResultSuccessAdded().get(lang);
        }
        else return resultConfig.result().getAddElementResultNotAdded().get(lang);
    }
    @Autowired
    AuthorBookRepository authorBookRepository;


    public String  removeAuthor(String firstName, String lastName, String patronymic, String lang) {
        for (AuthorBook ab :authorBookRepository.findAll()){
            if (ab.getIdAuthor()== authorRepository.getIdAuthor(firstName, lastName, patronymic)){
                return resultConfig.result().getHasReferences().get(lang);
            }
        }
        if (authorRepository.findById(authorRepository.getIdAuthor(firstName, lastName, patronymic)).isPresent()){
            authorRepository.deleteById(authorRepository.getIdAuthor(firstName, lastName, patronymic));
            return resultConfig.result().getRemoveElementResultSuccessRemoved().get(lang);
        }
        return resultConfig.result().getRemoveElementResultNotRemoved().get(lang);
    }

    public String updateAuthor(String oldFirstName, String oldLastName, String oldPatronymic,
                               String newLastName, String newFirstName, String newPatronymic, String lang) {
        if (authorRepository.findById(authorRepository.getIdAuthor(oldFirstName, oldLastName, oldPatronymic)).isPresent()){
            Author author = authorRepository.findById(authorRepository.getIdAuthor(oldFirstName, oldLastName, oldPatronymic)).get();
            author.setFirstName(newFirstName);
            author.setLastName(newLastName);
            author.setPatronymic(newPatronymic);

            authorRepository.save(author);
            return resultConfig.result().getModifyElementResultSuccessModified().get(lang);
        }
        return resultConfig.result().getModifyElementResultNotModified().get(lang);
    }

}
