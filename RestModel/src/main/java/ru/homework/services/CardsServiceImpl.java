package ru.homework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.homework.models.Card;
import ru.homework.models.Reader;
import ru.homework.repositories.CardsRepository;

import java.util.List;

@Component
public class CardsServiceImpl implements CardsService{

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public void addCard(Card card) {
        cardsRepository.save(card);
    }

    @Override
    public List<Card> findAll() {
        return cardsRepository.findAll();
    }

    @Override
    public List<Card> findAllByReader(Reader reader) {
        return cardsRepository.findAllByReader(reader);
    }

    @Override
    public void save(Card card) {
        cardsRepository.save(card);
    }
}
