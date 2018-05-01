package ru.homework.services;

import ru.homework.forms.SignInForm;
import ru.homework.transfer.TokenDto;

public interface SignInService {
    TokenDto login(SignInForm signInForm);
}
