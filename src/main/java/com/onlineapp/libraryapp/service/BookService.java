package com.onlineApp.libraryApp.services;

import com.onlineApp.libraryApp.model.*;
import com.onlineApp.libraryApp.model.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CoverRepository coverRepository;
    @Autowired
    EditionRepository editionRepository;
    @Autowired
    PublishYearRepository publishYearRepository;
    @Autowired
    AuthorBookRepository authorBookRepository;


    public ArrayList<HashMap<String, String>> getAllBooks() {

        ArrayList<HashMap<String, String>> booksWithInfo = new ArrayList<>();
        for(Book book:bookRepository.findAll()){
            booksWithInfo.add(getBookInfo(book));
        }

        return booksWithInfo;
    }

    public HashMap<String, String> getBookInfo(Book book){
        HashMap bookInfo = new HashMap<String, String>();

        bookInfo.put("Название", book.getName());
        bookInfo.put("Автор",  authorRepository.getAuthorByIdBook(book.getId()));
        bookInfo.put("Переплёт",  (coverRepository.findById(book.getCover())).get().getName());
        bookInfo.put("Количество изданий", (editionRepository.findById(book.getEdition())).get().getValue());
        bookInfo.put("Год издания", (publishYearRepository.findById(book.getPublishYear())).get().getYear());
        bookInfo.put("ISBN", book.getIsbn());

        return bookInfo;
    }

    public void addBook(String firstName, String lastName, String patronymic, String name, String publishYear, int edition, String cover, String isbn ){
        authorRepository.save(new Author(firstName, lastName, patronymic));
        coverRepository.save(new Cover(cover));
        editionRepository.save(new Edition(edition));
        publishYearRepository.save(new PublishYear(Integer.parseInt(publishYear)));



        bookRepository.save(new Book(name,
                publishYearRepository.getIdPublishByYear(publishYear),
                editionRepository.getIdEditionByValue(edition),
                coverRepository.getIdCoverByName(cover), 6, isbn));

        authorBookRepository.save(new AuthorBook(authorRepository.getIdAuthorByLastName(lastName), bookRepository.getIdBookByName(name)));

    }


    }

