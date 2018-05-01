package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.models.Card;
import ru.homework.repositories.BooksRepository;
import ru.homework.repositories.CardsRepository;
import ru.homework.repositories.ReadersRepository;
import ru.homework.repositories.TokensRepository;
import ru.homework.security.details.UserDetailsImpl;
import ru.homework.security.details.UserDetailsServiceImpl;
import ru.homework.services.BooksService;
import ru.homework.services.CardsService;
import ru.homework.services.UsersService;
import ru.homework.transfer.ReaderDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@RestController
public class ProfileController {

    @Autowired
    BooksService booksService;

    @Autowired
    UsersService usersService;

    @Autowired
    CardsService cardsService;

    @Autowired
    TokensRepository tokensRepository;

    private ReaderDto reader;

    @GetMapping("/")
    public ReaderDto getProfilePage(HttpServletRequest request, HttpServletResponse response) {
        reader = ReaderDto.from(tokensRepository.findOneByValue(request.getParameter("token")).get().getReader());
        request.setAttribute("freeBooks", booksService.findFreeBooks());
        request.setAttribute("yourBooks", booksService.findBooksByReaderName(reader.getFirstName(), reader.getLastName()));
        return reader;
    }

    @PostMapping("/")
    public ResponseEntity<Object> postProfilePage(String book) {
        String[] bookParts = new String[2];
        bookParts[0] = book.substring(1, book.lastIndexOf("\""));
        bookParts[1] = book.substring(book.lastIndexOf("\"") + 2);
        String bookTitle = bookParts[0];
        String bookAuthor = bookParts[1];
        Card card = new Card(Optional.of(usersService.getByFirstNameAndLastName(reader.getFirstName(), reader.getLastName())),
                booksService.getByTitleAndAuthor(bookTitle, bookAuthor));
        cardsService.save(card);
        return ResponseEntity.ok().build();
    }
}
