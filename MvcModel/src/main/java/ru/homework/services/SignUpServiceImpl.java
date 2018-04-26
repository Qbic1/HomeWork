package ru.homework.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.homework.forms.ReaderForm;
import ru.homework.models.Reader;
import ru.homework.models.Role;
import ru.homework.models.State;
import ru.homework.repositories.ReadersRepository;

@Service
public class SignUpServiceImpl implements SignUpService {
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
}
