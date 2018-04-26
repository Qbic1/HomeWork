package ru.homework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Card(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
    }
}
