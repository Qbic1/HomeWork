package ru.homework.services;

import ru.homework.forms.BookForm;
import ru.homework.models.Book;

import java.util.List;

public interface BooksService {
    void addBook(BookForm bookForm);

    Object findAllAuthors();

    List<Book> findAllByAuthor(String author);

    List<Book> findAll();

    List<Book> findFreeBooks();

    Book getByTitleAndAuthor(String title, String author);

    List<Book> findBooksByReaderName(String firstName, String lastName);
}
