package ru.homework.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.homework.forms.ReaderForm;
import ru.homework.models.Reader;
import ru.homework.models.Role;
import ru.homework.models.State;
import ru.homework.repositories.ReadersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private ReadersRepository readersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(ReaderForm form) {
        String hashPassword = passwordEncoder.encode(form.getPassword());

        Reader reader = Reader.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .age(form.getAge())
                .hashPassword(hashPassword)
                .login(form.getLogin())
                .role(Role.READER)
                .state(State.ACTIVE)
                .build();

        readersRepository.save(reader);
    }

    @Override
    public List<Reader> findAllByAgeBetween(int from, int to) {
        return readersRepository.findAllByAgeBetween(from, to);
    }

    @Override
    public List<Reader> findAll() {
        return readersRepository.findAll();
    }

    @Override
    public Optional<Reader> findOneByLogin(String login) {
        return readersRepository.findOneByLogin(login);
    }

    @Override
    public Reader getByFirstNameAndLastName(String firstName, String lastName) {
        return readersRepository.getByFirstNameAndLastName(firstName, lastName);
    }
}
