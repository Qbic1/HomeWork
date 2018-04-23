package ru.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.homework.models.Reader;

import java.util.List;

public interface ReadersRepository extends JpaRepository<Reader, Long>{
    List<Reader> findAllByAgeBetween(int from, int to);
    Reader getByFirstNameAndLastName(String firstName, String lastName);
}
