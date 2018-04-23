package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.homework.forms.BookForm;
import ru.homework.models.Book;
import ru.homework.repositories.BooksRepository;

import javax.validation.Valid;

@Controller
public class BooksController {
    @Autowired
    BooksRepository repository;

    @GetMapping("/books")
    public String getBooksPage(ModelMap model, @RequestParam(required = false, name = "authorForSearch") String author) {
        if (author != null && !author.equals("All"))
            model.addAttribute("booksFromServer", repository.findAllByAuthor(author));
        else
            model.addAttribute("booksFromServer", repository.findAll());
        model.addAttribute("authorList", repository.findAll());
        return "books";
    }

    @PostMapping("/books")
    public String AddBook(@Valid BookForm bookForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Book book = Book.from(bookForm);
            repository.save(book);
        }

        return "redirect:/books";
    }
}
