package com.uber.uber.controllers;

import com.uber.uber.models.Color;
import com.uber.uber.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(
        maxAge = 3600
)
@RestController
public class ColorController {
    @Autowired
    private ColorService service;


    @GetMapping("/colors")
    public List<Color> getColors() {
        return service.getColors();
    }
}
