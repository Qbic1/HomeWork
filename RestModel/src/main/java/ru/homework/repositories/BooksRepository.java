package ru.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.homework.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor(String author);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id NOT IN (SELECT book_id FROM card);")
    List<Book> findFreeBooks();

    Book getByTitleAndAuthor(String title, String author);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id IN (SELECT book_id FROM card INNER JOIN reader ON (card.reader_id=reader.id) WHERE first_name=?1 AND last_name=?2);")
    List<Book> findBooksByReaderName(String firstName, String lastName);

    @Query(nativeQuery = true, value = "SELECT author FROM book")
    List<String> findAllAuthors();
}