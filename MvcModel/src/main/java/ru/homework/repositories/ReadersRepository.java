package ru.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.models.Reader;

import java.util.List;
import java.util.Optional;

public interface ReadersRepository extends JpaRepository<Reader, Long>{
    List<Reader> findAllByAgeBetween(int from, int to);
    Reader getByFirstNameAndLastName(String firstName, String lastName);
    Optional<Reader> findOneByLogin(String login);
}
