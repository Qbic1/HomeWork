package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.models.Card;
import ru.homework.models.Reader;
import ru.homework.services.BooksService;
import ru.homework.services.CardsService;
import ru.homework.services.UsersService;
import ru.homework.transfer.CardDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
public class CardsController {
    @Autowired
    CardsService cardsService;

    @Autowired
    UsersService usersService;

    @Autowired
    BooksService booksService;

    @GetMapping("/cards")
    public List<CardDto> getCardsPage(ModelMap model, @RequestParam(required = false, name = "readerForSearch") String readerString,
                                      HttpServletRequest request) {
        List<CardDto> cards;
        if(readerString!=null && !readerString.equals("All")) {
            String[] readerParts = readerString.split(" ");
            String readerFirstName = readerParts[0];
            String readerLastName = readerParts[1];
            Reader reader = usersService.getByFirstNameAndLastName(readerFirstName, readerLastName);
            cards = CardDto.from(cardsService.findAllByReader(reader));
        }
        else
            cards = CardDto.from(cardsService.findAll());
        request.setAttribute("readersFromServer", usersService.findAll());
        request.setAttribute("freeBooks", booksService.findFreeBooks());
        return cards;
    }

    @PostMapping("/cards")
    public ResponseEntity<Object> AddReader(String reader, String book) {
        String[] readerParts = reader.split(" ");
        String readerFirstName = readerParts[0];
        String readerLastName = readerParts[1];
        String[] bookParts = new String[2];
        bookParts[0] = book.substring(1, book.lastIndexOf("\""));
        bookParts[1] = book.substring(book.lastIndexOf("\"") + 2);
        String bookTitle = bookParts[0];
        String bookAuthor = bookParts[1];
        Card card = new Card(Optional.of(usersService.getByFirstNameAndLastName(readerFirstName, readerLastName)),
                booksService.getByTitleAndAuthor(bookTitle, bookAuthor));
        cardsService.addCard(card);
        return ResponseEntity.ok().build();
    }
}
