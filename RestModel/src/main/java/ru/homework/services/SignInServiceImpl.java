package ru.homework.services;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.homework.forms.SignInForm;
import ru.homework.models.Reader;
import ru.homework.models.Token;
import ru.homework.repositories.ReadersRepository;
import ru.homework.repositories.TokensRepository;
import ru.homework.transfer.TokenDto;

import java.util.Optional;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReadersRepository readersRepository;

    @Override
    public TokenDto login(SignInForm signInForm) {
        Optional<Reader> readerCandidate = readersRepository.findOneByLogin(signInForm.getLogin());

        if (readerCandidate.isPresent()) {
            Reader reader = readerCandidate.get();

            if(passwordEncoder.matches(signInForm.getPassword(), reader.getHashPassword()))
            {
                Token token = Token.builder()
                        .reader(reader)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokensRepository.save(token);
                return TokenDto.from(token);
            }
        }
        throw new IllegalStateException("Reader not found");
    }
}
