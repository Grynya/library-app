package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.config.ResultConfig;
import com.onlineapp.libraryapp.model.Book;
import com.onlineapp.libraryapp.model.Author;
import com.onlineapp.libraryapp.model.Cover;
import com.onlineapp.libraryapp.model.PublishYear;
import com.onlineapp.libraryapp.model.Edition;
import com.onlineapp.libraryapp.model.AuthorBook;

import com.onlineapp.libraryapp.model.repo.BookRepository;
import com.onlineapp.libraryapp.model.repo.AuthorRepository;
import com.onlineapp.libraryapp.model.repo.CoverRepository;
import com.onlineapp.libraryapp.model.repo.EditionRepository;
import com.onlineapp.libraryapp.model.repo.PublishYearRepository;
import com.onlineapp.libraryapp.model.repo.AuthorBookRepository;

import com.onlineapp.libraryapp.model.request.AddBookRequest;
import com.onlineapp.libraryapp.model.request.UpdateBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final CoverRepository coverRepository;
    @Autowired
    private final EditionRepository editionRepository;
    @Autowired
    private final PublishYearRepository publishYearRepository;
    @Autowired
    private final AuthorBookRepository authorBookRepository;

    @Autowired
    private final ResultConfig resultConfig;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CoverRepository coverRepository, EditionRepository editionRepository, PublishYearRepository publishYearRepository, AuthorBookRepository authorBookRepository, ResultConfig resultConfig) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.coverRepository = coverRepository;
        this.editionRepository = editionRepository;
        this.publishYearRepository = publishYearRepository;
        this.authorBookRepository = authorBookRepository;
        this.resultConfig = resultConfig;
    }


    public List<Map<String, String>> books() {

        final List<Map<String, String>> booksWithInfo = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            booksWithInfo.add(getBookInfo(book));
        }

        return booksWithInfo;
    }

    public Map<String, String> getBookInfo(Book book) {
        final HashMap bookInfo = new HashMap<String, String>();

        bookInfo.put("Name", book.getName());
        bookInfo.put("Author", !authorRepository.getAuthorByIdBook(book.getId()).isEmpty() ?
                authorRepository.getAuthorByIdBook(book.getId()) : null);
        bookInfo.put("Cover", coverRepository.findById(book.getCover()).isPresent() ?
                coverRepository.findById(book.getCover()).get().getName() : null);
        bookInfo.put("Editions", editionRepository.findById(book.getEdition()).isPresent() ?
                editionRepository.findById(book.getEdition()).get().getValue() : null);
        bookInfo.put("Publish year", publishYearRepository.findById(book.getPublishYear()).isPresent() ?
                publishYearRepository.findById(book.getPublishYear()).get().getYear() : null);
        bookInfo.put("ISBN", book.getIsbn());
        return bookInfo;
    }

    public String addBook(AddBookRequest addBookRequest) {
        if (bookRepository.checkForDuplicate(addBookRequest.getName().replaceAll("\\s+", ""), addBookRequest.getIsbn().replaceAll("\\s+", "")).isEmpty()) {

            if (authorRepository.checkForDuplicate(addBookRequest.getFirstName().replaceAll("\\s+", ""), addBookRequest.getLastName().replaceAll("\\s+", ""), addBookRequest.getPatronymic().replaceAll("\\s+", "")).isEmpty()) {
                authorRepository.save(new Author(addBookRequest.getFirstName(), addBookRequest.getLastName(), addBookRequest.getPatronymic()));
            }
            if (coverRepository.checkForDuplicate(addBookRequest.getCover().replaceAll("\\s+", "")).isEmpty()) {
                coverRepository.save(new Cover(addBookRequest.getCover()));
            }
            if (editionRepository.checkForDuplicate(Integer.parseInt(addBookRequest.getEdition().replaceAll("\\s+", ""))).isEmpty()) {
                editionRepository.save(new Edition(Integer.parseInt(addBookRequest.getEdition().replaceAll("\\s+", ""))));
            }
            if (publishYearRepository.checkForDuplicate(Integer.parseInt(addBookRequest.getPublishYear().replaceAll("\\s+", ""))).isEmpty()) {
                publishYearRepository.save(new PublishYear(Integer.parseInt(addBookRequest.getPublishYear().replaceAll("\\s+", ""))));
            }

            bookRepository.save(new Book(addBookRequest.getName(),
                    publishYearRepository.getIdPublishByYear(Integer.parseInt(addBookRequest.getPublishYear())),
                    editionRepository.getIdEditionByValue(Integer.parseInt(addBookRequest.getEdition())),
                    coverRepository.getIdCoverByName(addBookRequest.getCover()), addBookRequest.getIsbn()));
            authorBookRepository.save(new AuthorBook(authorRepository.getIdAuthor(addBookRequest.getFirstName(), addBookRequest.getLastName(), addBookRequest.getPatronymic()), bookRepository.getIdBookByNameAndIsbn(addBookRequest.getName(), addBookRequest.getIsbn())));
            return resultConfig.result().getAddElementResultSuccessAdded().get(addBookRequest.getLang());
        } else return resultConfig.result().getAddElementResultNotAdded().get(addBookRequest.getLang());

    }

    public String removeBook(String name, String isbn, String lang) {
        if (bookRepository.findById(bookRepository.getIdBookByNameAndIsbn(name, isbn)).isPresent()) {

            authorBookRepository.deleteById(authorBookRepository
                    .getIdAuthorBookByBook(bookRepository
                            .getIdBookByNameAndIsbn(name, isbn)));

            bookRepository
                    .deleteById(bookRepository
                            .getIdBookByNameAndIsbn(name, isbn));

            return resultConfig.result().getRemoveElementResultSuccessRemoved().get(lang);
        }
        return resultConfig.result().getRemoveElementResultNotRemoved().get(lang);

    }

    public String updateBook(UpdateBookRequest updateBookRequest) {
        if (bookRepository.getIdBookByNameAndIsbn(updateBookRequest.getOldName(), updateBookRequest.getOldIsbn()) != -1) {
            int idBook = bookRepository.getIdBookByNameAndIsbn(updateBookRequest.getOldName(), updateBookRequest.getOldIsbn());

            if (bookRepository.findById(idBook).isPresent()) {
                Book book = bookRepository.findById(idBook).get();

                //there is updateAuthor() to change author partially (to change only firstName or lastName),
                // here we change author completely or leave it old
                authorRepository.save(new Author(
                        updateBookRequest.getNewFirstName(), updateBookRequest.getNewLastName(), updateBookRequest.getNewPatronymic()));
                if (authorBookRepository.findById(
                        authorBookRepository.getIdAuthorBookByBook(idBook)).isPresent()) {
                    AuthorBook authorBook = authorBookRepository.findById(
                            authorBookRepository.getIdAuthorBookByBook(idBook)).get();
                    authorBook.setIdAuthor(authorRepository.getIdAuthor(
                            updateBookRequest.getNewFirstName(), updateBookRequest.getNewLastName(), updateBookRequest.getNewPatronymic()));
                    authorBookRepository.save(authorBook);
                }
                if (coverRepository.getIdCoverByName(updateBookRequest.getNewCover()) != book.getCover()) {
                    if (coverRepository.getIdCoverByName(updateBookRequest.getNewCover()) == -1) {
                        coverRepository.save(new Cover(updateBookRequest.getNewCover()));
                    }
                    book.setCover(coverRepository.getIdCoverByName(updateBookRequest.getNewCover()));
                }

                if (publishYearRepository.getIdPublishByYear(Integer.parseInt(updateBookRequest.getNewPublishYear())) != book.getPublishYear()) {
                    if (publishYearRepository.getIdPublishByYear(Integer.parseInt(updateBookRequest.getNewPublishYear())) == -1) {
                        publishYearRepository.save(new PublishYear(Integer.parseInt(updateBookRequest.getNewPublishYear())));
                    }
                    book.setPublishYear(publishYearRepository.getIdPublishByYear(Integer.parseInt(updateBookRequest.getNewPublishYear())));
                }
                if (editionRepository.getIdEditionByValue(Integer.parseInt(updateBookRequest.getNewEdition())) != book.getEdition()) {
                    if (editionRepository.getIdEditionByValue(Integer.parseInt(updateBookRequest.getNewEdition())) == -1) {
                        editionRepository.save(new Edition(Integer.parseInt(updateBookRequest.getNewEdition())));
                    }
                    book.setEdition(editionRepository.getIdEditionByValue(Integer.parseInt(updateBookRequest.getNewEdition())));
                }
                if (!book.getName().equals(updateBookRequest.getNewName())) {
                    book.setName(updateBookRequest.getNewName());
                }
                if (!book.getIsbn().equals(updateBookRequest.getNewIsbn())) {
                    book.setIsbn(updateBookRequest.getNewIsbn());
                }
                bookRepository.save(book);
                return resultConfig.result().getModifyElementResultSuccessModified().get(updateBookRequest.getLang());
            }
        }
        return resultConfig.result().getModifyElementResultNotModified().get(updateBookRequest.getLang());
    }

}