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
        for(Book book:bookRepository.findAll()){
            booksWithInfo.add(getBookInfo(book));
        }

        return booksWithInfo;
    }

    public Map<String, String> getBookInfo(Book book){
        final HashMap bookInfo = new HashMap<String, String>();

        bookInfo.put("Name", book.getName());
        bookInfo.put("Author",  !authorRepository.getAuthorByIdBook(book.getId()).isEmpty()?
                authorRepository.getAuthorByIdBook(book.getId()):null);
        bookInfo.put("Cover", coverRepository.findById(book.getCover()).isPresent()?
                coverRepository.findById(book.getCover()).get().getName():null);
        bookInfo.put("Editions", editionRepository.findById(book.getEdition()).isPresent()?
                editionRepository.findById(book.getEdition()).get().getValue():null);
        bookInfo.put("Publish year", publishYearRepository.findById(book.getPublishYear()).isPresent()?
                publishYearRepository.findById(book.getPublishYear()).get().getYear():null);
        bookInfo.put("ISBN", book.getIsbn());
        return bookInfo;
    }

    public String addBook(String firstName, String lastName, String patronymic, String name, String publishYear, String edition, String cover, String isbn, String lang){
        System.out.println(edition);
        if(bookRepository.checkForDuplicate(name.replaceAll("\\s+",""), isbn.replaceAll("\\s+","")).isEmpty()) {

            if (authorRepository.checkForDuplicate(firstName.replaceAll("\\s+",""), lastName.replaceAll("\\s+",""), patronymic.replaceAll("\\s+","")).isEmpty()){
                authorRepository.save(new Author(firstName, lastName, patronymic));
            }
            if(coverRepository.checkForDuplicate(cover.replaceAll("\\s+","")).isEmpty()){
                coverRepository.save(new Cover(cover));
            }
            if(editionRepository.checkForDuplicate(Integer.parseInt(edition.replaceAll("\\s+",""))).isEmpty()) {
                editionRepository.save(new Edition(Integer.parseInt(edition.replaceAll("\\s+",""))));
            }
            if(publishYearRepository.checkForDuplicate(Integer.parseInt(publishYear.replaceAll("\\s+",""))).isEmpty()){
                publishYearRepository.save(new PublishYear(Integer.parseInt(publishYear.replaceAll("\\s+",""))));
            }

            bookRepository.save(new Book(name,
                    publishYearRepository.getIdPublishByYear(Integer.parseInt(publishYear)),
                    editionRepository.getIdEditionByValue(Integer.parseInt(edition)),
                    coverRepository.getIdCoverByName(cover), isbn));
            authorBookRepository.save(new AuthorBook(authorRepository.getIdAuthor(firstName, lastName, patronymic), bookRepository.getIdBookByNameAndIsbn(name, isbn)));
            return resultConfig.result().getAddElementResultSuccessAdded().get(lang);
        }
        else return resultConfig.result().getAddElementResultNotAdded().get(lang);

    }

    public String removeBook(String name, String isbn, String lang ){
        if (bookRepository.findById(bookRepository.getIdBookByNameAndIsbn(name, isbn)).isPresent()){

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
    public String updateBook(String oldName, String oldIsbn,
                              String newFirstName, String newLastName, String newPatronymic,
                              String newName, Integer newPublishYear, Integer newEdition,
                              String newCover, String newIsbn, String lang){
        if (bookRepository.getIdBookByNameAndIsbn(oldName, oldIsbn)!=-1) {
            int idBook = bookRepository.getIdBookByNameAndIsbn(oldName, oldIsbn);

            if(bookRepository.findById(idBook).isPresent()) {
                Book book = bookRepository.findById(idBook).get();

                //there is updateAuthor() to change author partially (to change only firstName or lastName),
                // here we change author completely or leave it old
                authorRepository.save(new Author(newFirstName, newLastName, newFirstName));
                if (authorBookRepository.findById(
                        authorBookRepository.getIdAuthorBookByBook(idBook)).isPresent()) {
                    AuthorBook authorBook = authorBookRepository.findById(
                            authorBookRepository.getIdAuthorBookByBook(idBook)).get();
                    authorBook.setIdAuthor(authorRepository.getIdAuthor(newFirstName, newLastName, newPatronymic));
                    authorBookRepository.save(authorBook);
                }
                if (coverRepository.getIdCoverByName(newCover) != book.getCover()) {
                    if (coverRepository.getIdCoverByName(newCover) == -1) {
                        coverRepository.save(new Cover(newCover));
                    }
                    book.setCover(coverRepository.getIdCoverByName(newCover));
                }

                if (publishYearRepository.getIdPublishByYear(newPublishYear) != book.getPublishYear()) {
                    if (publishYearRepository.getIdPublishByYear(newPublishYear) == -1) {
                        publishYearRepository.save(new PublishYear(newPublishYear));
                    }
                    book.setPublishYear(publishYearRepository.getIdPublishByYear(newPublishYear));
                }
                if (editionRepository.getIdEditionByValue(newEdition) != book.getEdition()) {
                    if (editionRepository.getIdEditionByValue(newEdition) == -1) {
                        editionRepository.save(new Edition(newEdition));
                    }
                    book.setEdition(editionRepository.getIdEditionByValue(newEdition));
                }
                if (!book.getName().equals(newCover)) {
                    book.setName(newName);
                }
                if(!book.getIsbn().equals(newIsbn)){
                    book.setIsbn(newIsbn);
                }
                    bookRepository.save(book);
                }

            return resultConfig.result().getModifyElementResultSuccessModified().get(lang);
        }
        else return resultConfig.result().getModifyElementResultNotModified().get(lang);
    }

    }