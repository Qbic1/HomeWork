package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.homework.models.Card;
import ru.homework.models.Reader;
import ru.homework.repositories.BooksRepository;
import ru.homework.repositories.CardsRepository;
import ru.homework.repositories.ReadersRepository;


@Controller
public class CardsController {
    @Autowired
    CardsRepository repository;

    @Autowired
    ReadersRepository readersRepository;

    @Autowired
    BooksRepository booksRepository;

    @GetMapping("/cards")
    public String getCardsPage(ModelMap model, @RequestParam(required = false, name = "readerForSearch") String readerString) {
        if(readerString!=null && !readerString.equals("All")) {
            String[] readerParts = readerString.split(" ");
            String readerFirstName = readerParts[0];
            String readerLastName = readerParts[1];
            Reader reader = readersRepository.getByFirstNameAndLastName(readerFirstName, readerLastName);
            model.addAttribute("cardsFromServer", repository.findAllByReader(reader));
        }
        else
            model.addAttribute("cardsFromServer", repository.findAll());
        model.addAttribute("readers", readersRepository.findAll());
        model.addAttribute("freeBooks", booksRepository.findFreeBooks());
        return "cards";
    }

    @PostMapping("/cards")
    public String AddReader(String reader, String book) {
        String[] readerParts = reader.split(" ");
        String readerFirstName = readerParts[0];
        String readerLastName = readerParts[1];
        String[] bookParts = new String[2];
        bookParts[0] = book.substring(1, book.lastIndexOf("\""));
        bookParts[1] = book.substring(book.lastIndexOf("\"") + 2);
        String bookTitle = bookParts[0];
        String bookAuthor = bookParts[1];
        Card card = new Card(readersRepository.getByFirstNameAndLastName(readerFirstName, readerLastName),
                booksRepository.getByTitleAndAuthor(bookTitle, bookAuthor));
        repository.save(card);
        return "redirect:/cards";
    }
}
