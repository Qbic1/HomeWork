package ru.homework.services;

import ru.homework.forms.ReaderForm;
import ru.homework.models.Reader;
import ru.homework.transfer.ReaderDto;

import java.util.List;

public interface UsersService {
    void signUp(ReaderForm form);

    List<Reader> findAllByAgeBetween(int from, int to);

    List<Reader> findAll();
}
