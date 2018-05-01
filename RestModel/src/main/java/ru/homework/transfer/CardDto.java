package ru.homework.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.access.method.P;
import ru.homework.models.Card;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class CardDto {
    private ReaderDto reader;
    private BookDto book;

    public static CardDto from(Card card) {
        return CardDto.builder()
                .book(BookDto.from(card.getBook()))
                .reader(ReaderDto.from(card.getReader()))
                .build();
    }

    public static List<CardDto> from(List<Card> cards) {
        return cards.stream().map(CardDto::from).collect(Collectors.toList());
    }
}
