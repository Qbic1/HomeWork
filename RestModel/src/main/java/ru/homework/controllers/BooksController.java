package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.forms.BookForm;
import ru.homework.services.BooksService;
import ru.homework.transfer.BookDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BooksController {
    @Autowired
    BooksService booksService;

    @GetMapping("/books")
    public List<BookDto> getBooksPage(@RequestParam(required = false, name = "authorForSearch") String author, HttpServletRequest request)
    {
        request.setAttribute("authorList", booksService.findAllAuthors());
        if (author != null && !author.equals("All"))
            return BookDto.from(booksService.findAllByAuthor(author));
        else
            return BookDto.from(booksService.findAll());
    }

    @PostMapping("/books")
    public ResponseEntity<Object> AddBook(@RequestBody BookForm bookForm) {
        booksService.addBook(bookForm);
        return ResponseEntity.ok().build();
    }
}
