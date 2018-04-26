package ru.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.models.Card;
import ru.homework.models.Reader;

import java.util.List;


public interface CardsRepository extends JpaRepository<Card, Long>{
    List<Card> findAllByReader(Reader reader);
}
