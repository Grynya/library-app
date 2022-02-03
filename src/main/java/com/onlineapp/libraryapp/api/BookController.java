package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.model.Book;
import com.onlineapp.libraryapp.model.request.AddBookRequest;
import com.onlineapp.libraryapp.model.request.UpdateBookRequest;
import com.onlineapp.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Map<String, String>> booksInAddElements() {
        return bookService.books();
    }

    @PostMapping("/book")
    public String addBook(AddBookRequest addBookRequest) {
        return bookService.addBook(addBookRequest);
    }

    @DeleteMapping("/book")
    public String removeBook(@RequestParam String name, @RequestParam String isbn, @RequestParam String lang) {
        return bookService.removeBook(name, isbn, lang);
    }

    @PutMapping("/book")
    public String updateCover(UpdateBookRequest updateBookRequest) {
        return bookService.updateBook(updateBookRequest);
    }
}