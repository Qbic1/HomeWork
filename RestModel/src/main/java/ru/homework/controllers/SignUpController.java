package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.forms.ReaderForm;
import ru.homework.services.UsersService;

@RestController
public class SignUpController {
    @Autowired
    private UsersService service;

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(ReaderForm readerForm) {
        service.signUp(readerForm);
        return ResponseEntity.ok().build();
    }
}
