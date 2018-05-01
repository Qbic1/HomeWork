package ru.homework.services;

import ru.homework.models.Card;
import ru.homework.models.Reader;

import java.util.List;

public interface CardsService {
    List<Card> findAllByReader(Reader reader);

    List<Card> findAll();

    void addCard(Card card);

    void save(Card card);
}
