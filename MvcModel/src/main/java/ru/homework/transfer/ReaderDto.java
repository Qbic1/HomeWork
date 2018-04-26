package ru.homework.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.homework.models.Reader;

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
}
