package ru.homework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.homework.forms.BookForm;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @OneToOne(mappedBy = "book")
    private Card card;

    @Override
    public String toString() {
        return '\"' + title + "\" " + author;
    }

    public static Book from(BookForm form) {
        return Book.builder()
                .title(form.getTitle())
                .author(form.getAuthor())
                .build();
    }
}
