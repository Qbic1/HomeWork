package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.homework.forms.ReaderForm;
import ru.homework.models.Reader;
import ru.homework.services.UsersService;
import ru.homework.transfer.ReaderDto;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReadersController {

    @Autowired
    UsersService service;

    @GetMapping("/readers")
    public List<ReaderDto> getReadersPage(@RequestParam(required = false, name = "ageFrom") String from,
                                          @RequestParam(required = false, name = "ageTo") String to) {
        if (from != null && to != null)
            return ReaderDto.from(service.findAllByAgeBetween(Integer.parseInt(from), Integer.parseInt(to)));
        else
            return ReaderDto.from(service.findAll());
    }

    @PostMapping("/readers")
    public ResponseEntity<Object> addReader(@RequestBody ReaderForm readerForm) {
        service.signUp(readerForm);
        return ResponseEntity.ok().build();
    }
}
