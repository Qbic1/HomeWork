package ru.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @GetMapping("/library")
    public String getLibraryPage() {
        return "library";
    }

}