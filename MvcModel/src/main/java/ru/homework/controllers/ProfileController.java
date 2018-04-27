package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.homework.models.Book;
import ru.homework.models.Card;
import ru.homework.models.Reader;
import ru.homework.repositories.BooksRepository;
import ru.homework.repositories.CardsRepository;
import ru.homework.repositories.ReadersRepository;
import ru.homework.security.details.UserDetailsImpl;
import ru.homework.transfer.ReaderDto;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;


@Controller
public class ProfileController {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    ReadersRepository readersRepository;

    @Autowired
    CardsRepository cardsRepository;

    UserDetailsImpl details;

    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        details = (UserDetailsImpl) authentication.getPrincipal();
        ReaderDto reader = ReaderDto.from(details.getReader());
        model.addAttribute("reader", reader);
        model.addAttribute("freeBooks", booksRepository.findFreeBooks());
        List<Book> books = booksRepository.findBooksByLogin(details.getUsername());
        model.addAttribute("yourBooks", books);
        return "profile";
    }

    @PostMapping("/")
    public String postProfilePage(String book) {
        String[] bookParts = new String[2];
        bookParts[0] = book.substring(1, book.lastIndexOf("\""));
        bookParts[1] = book.substring(book.lastIndexOf("\"") + 2);
        String bookTitle = bookParts[0];
        String bookAuthor = bookParts[1];
        Card card = new Card(readersRepository.findOneByLogin(details.getUsername()),
                booksRepository.getByTitleAndAuthor(bookTitle, bookAuthor));
        cardsRepository.save(card);
        return "redirect:/";
    }
}
