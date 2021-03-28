package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.config.ResultConfig;
import com.onlineapp.libraryapp.model.Author;
import com.onlineapp.libraryapp.model.AuthorBook;
import com.onlineapp.libraryapp.model.repo.AuthorBookRepository;
import com.onlineapp.libraryapp.model.repo.AuthorRepository;
import com.onlineapp.libraryapp.model.request.AddAuthorRequest;
import com.onlineapp.libraryapp.model.request.RemoveAuthorRequest;
import com.onlineapp.libraryapp.model.request.UpdateAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return authorRepository.findAll();
    }

    public String addAuthor(AddAuthorRequest addAuthorRequest) {
        if (authorRepository.checkForDuplicate(addAuthorRequest.getFirstName().replaceAll("\\s+", ""), addAuthorRequest.getLastName().replaceAll("\\s+", ""), addAuthorRequest.getPatronymic().replaceAll("\\s+", "")).isEmpty()) {
            authorRepository.save(new Author(addAuthorRequest.getFirstName(), addAuthorRequest.getLastName(), addAuthorRequest.getPatronymic()));
            return resultConfig.result().getAddElementResultSuccessAdded().get(addAuthorRequest.getLang());
        } else return resultConfig.result().getAddElementResultNotAdded().get(addAuthorRequest.getLang());
    }

    @Autowired
    AuthorBookRepository authorBookRepository;


    public String removeAuthor(RemoveAuthorRequest removeAuthorRequest) {
        for (AuthorBook ab : authorBookRepository.findAll()) {
            if (ab.getIdAuthor() == authorRepository.getIdAuthor(removeAuthorRequest.getFirstName(), removeAuthorRequest.getLastName(), removeAuthorRequest.getPatronymic())) {
                return resultConfig.result().getHasReferences().get(removeAuthorRequest.getPatronymic());
            }
        }
        if (authorRepository.findById(authorRepository.getIdAuthor(removeAuthorRequest.getFirstName(), removeAuthorRequest.getLastName(), removeAuthorRequest.getPatronymic())).isPresent()) {
            authorRepository.deleteById(authorRepository.getIdAuthor(removeAuthorRequest.getFirstName(), removeAuthorRequest.getLastName(), removeAuthorRequest.getPatronymic()));
            return resultConfig.result().getRemoveElementResultSuccessRemoved().get(removeAuthorRequest.getLang());
        }
        return resultConfig.result().getRemoveElementResultNotRemoved().get(removeAuthorRequest.getLang());
    }

    public String updateAuthor(UpdateAuthorRequest updateAuthorRequest) {
        if (authorRepository.findById(authorRepository.getIdAuthor(updateAuthorRequest.getOldFirstName(), updateAuthorRequest.getOldLastName(), updateAuthorRequest.getOldPatronymic())).isPresent()) {
            Author author = authorRepository.findById(authorRepository.getIdAuthor(updateAuthorRequest.getOldFirstName(), updateAuthorRequest.getOldLastName(), updateAuthorRequest.getOldPatronymic())).get();
            author.setFirstName(updateAuthorRequest.getNewFirstName());
            author.setLastName(updateAuthorRequest.getNewLastName());
            author.setPatronymic(updateAuthorRequest.getNewPatronymic());

            authorRepository.save(author);
            return resultConfig.result().getModifyElementResultSuccessModified().get(updateAuthorRequest.getLang());
        }
        return resultConfig.result().getModifyElementResultNotModified().get(updateAuthorRequest.getLang());
    }

}
