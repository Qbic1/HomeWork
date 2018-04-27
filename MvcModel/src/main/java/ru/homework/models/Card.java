package ru.homework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "readerId")
    private Reader reader;

    @OneToOne
    @JoinColumn(name = "bookId")
    private Book book;

    public Card(Optional<Reader> reader, Book book) {
        this.reader = reader.get();
        this.book = book;
    }
}
