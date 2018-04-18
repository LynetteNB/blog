package com.example.blog.controllers;

import com.example.blog.services.CalculateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    private final CalculateService calculateService;

    public MathController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public int add(@PathVariable int num1, @PathVariable int num2) {
        return num1 + num2;
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public int subtract(@PathVariable int num1, @PathVariable int num2) {
        return num2 - num1;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public int multiply(@PathVariable int num1, @PathVariable int num2) {
        return num1 * num2;
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public int divide(@PathVariable int num1, @PathVariable int num2) {
        return num1 / num2;
    }

    @GetMapping("/calculate/{side}")
    @ResponseBody
    public String viewMathInfo (@PathVariable int side) {
        return calculateService.calculateInfo(side);
    }
}
