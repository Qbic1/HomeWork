package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.homework.forms.ReaderForm;
import ru.homework.models.Reader;
import ru.homework.repositories.ReadersRepository;

import javax.validation.Valid;

@Controller
public class ReadersController {

    @Autowired
    ReadersRepository repository;

    @GetMapping("/readers")
    public String getReadersPage(ModelMap model, @RequestParam(required = false, name = "ageFrom") String from,
                                 @RequestParam(required = false, name = "ageTo") String to) {
        if (from != null && to != null)
            model.addAttribute("readersFromServer", repository.findAllByAgeBetween(Integer.parseInt(from), Integer.parseInt(to)));
        else
            model.addAttribute("readersFromServer", repository.findAll());
        return "readers";
    }

    @PostMapping("/readers")
    public String AddReader(@Valid ReaderForm readerForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Reader reader = Reader.from(readerForm);
            repository.save(reader);
        }

        return "redirect:/readers";
    }
}
