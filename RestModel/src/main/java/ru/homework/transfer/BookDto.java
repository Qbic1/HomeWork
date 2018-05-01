package ru.homework.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.homework.models.Book;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class BookDto {
    private String title;
    private String author;

    public static BookDto from(Book book) {
        return BookDto.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }

    public static List<BookDto> from(List<Book> books) {
        return books.stream().map(BookDto::from).collect(Collectors.toList());
    }

}
