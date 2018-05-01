package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.forms.SignInForm;
import ru.homework.services.SignInService;
import ru.homework.transfer.TokenDto;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SignInController {

    @Autowired
    SignInService signInService;

    @PostMapping("signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInForm signInForm)
    {
        return ResponseEntity.ok(signInService.login(signInForm));
    }
}
