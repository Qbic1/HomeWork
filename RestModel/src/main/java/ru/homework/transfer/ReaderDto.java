package ru.homework.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.homework.models.Reader;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class ReaderDto {
    private String firstName;
    private String lastName;
    private Integer age;

    public static ReaderDto from(Reader reader) {
        return ReaderDto.builder()
                .firstName(reader.getFirstName())
                .lastName(reader.getLastName())
                .age(reader.getAge())
                .build();
    }
    public static List<ReaderDto> from(List<Reader> readers) {
        return readers.stream().map(ReaderDto::from).collect(Collectors.toList());
    }
}
