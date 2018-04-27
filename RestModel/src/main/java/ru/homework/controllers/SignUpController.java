package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.homework.forms.ReaderForm;
import ru.homework.services.UsersService;

@Controller
public class SignUpController {
    @Autowired
    private UsersService service;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(ReaderForm readerForm) {
        service.signUp(readerForm);
        return "redirect:/signIn";
    }
}
