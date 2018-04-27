package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.homework.repositories.BooksRepository;
import ru.homework.repositories.CardsRepository;
import ru.homework.repositories.ReadersRepository;



@Controller
public class ProfileController {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    ReadersRepository readersRepository;

    @Autowired
    CardsRepository cardsRepository;

    //UserDetailsImpl details;

//    @GetMapping("/")
//    public String getProfilePage(ModelMap model, Authentication authentication) {
//        if (authentication == null) {
//            return "redirect:/login";
//        }
//        details = (UserDetailsImpl) authentication.getPrincipal();
//        ReaderDto reader = ReaderDto.from(details.getReader());
//        model.addAttribute("reader", reader);
//        model.addAttribute("freeBooks", booksRepository.findFreeBooks());
//        List<Book> books = booksRepository.findBooksByLogin(details.getUsername());
//        model.addAttribute("yourBooks", books);
//        return "profile";
//    }

//    @PostMapping("/")
//    public String postProfilePage(String book) {
//        String[] bookParts = new String[2];
//        bookParts[0] = book.substring(1, book.lastIndexOf("\""));
//        bookParts[1] = book.substring(book.lastIndexOf("\"") + 2);
//        String bookTitle = bookParts[0];
//        String bookAuthor = bookParts[1];
//        Card card = new Card(readersRepository.findOneByLogin(details.getUsername()),
//                booksRepository.getByTitleAndAuthor(bookTitle, bookAuthor));
//        cardsRepository.save(card);
//        return "redirect:/";
//    }
}
