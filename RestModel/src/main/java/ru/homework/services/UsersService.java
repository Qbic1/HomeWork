package ru.homework.services;

import ru.homework.forms.ReaderForm;
import ru.homework.models.Reader;
import ru.homework.transfer.ReaderDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    void signUp(ReaderForm form);

    List<Reader> findAllByAgeBetween(int from, int to);

    List<Reader> findAll();

    Reader getByFirstNameAndLastName(String firstName, String lastName);

    Optional<Reader> findOneByLogin(String login);
}
