package ru.homework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.homework.forms.BookForm;
import ru.homework.models.Book;
import ru.homework.repositories.BooksRepository;

import java.util.List;

@Component
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository repository;

    @Override
    public void addBook(BookForm bookForm) {
        Book book = Book.from(bookForm);
        repository.save(book);
    }

    @Override
    public List<Book> findFreeBooks() {
        return repository.findFreeBooks();
    }

    @Override
    public Book getByTitleAndAuthor(String title, String author) {
        return repository.getByTitleAndAuthor(title, author);
    }

    @Override
    public Object findAllAuthors() {
        return repository.findAllAuthors();
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        return repository.findAllByAuthor(author);
    }

    @Override
    public List<Book> findBooksByReaderName(String firstName, String lastName) {
        return repository.findBooksByReaderName(firstName, lastName);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }
}
