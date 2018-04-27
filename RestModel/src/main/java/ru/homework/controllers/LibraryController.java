package ru.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @GetMapping("/library")
    public String getLibraryPage() {
        return "library";
    }

}