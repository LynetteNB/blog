package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.thymeleaf.util.NumberUtils.sequence;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice(Model model) {
        Integer[] diceSides = sequence(1, 6);
        model.addAttribute("diceSides", diceSides);
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDiceGuess(@PathVariable int n, Model model) {
        int diceRolls = 6;
        List<Integer> rand = getRandomNumList(diceRolls);
        String message = "You guessed " + n + ". The " + diceRolls + " dice rolls were: ";
        int count = 0;
        for(int num : rand) {
            message += "[" + num + "]";
            if(n == num) {
                count += 1;
            }
        }
        message += count == 0 ? ". You did not match any rolls." : ". Your guess matched " + count + " roll(s)!";
        model.addAttribute("n", n);
        model.addAttribute("message", message);
        return "roll-dice";
    }

    public List<Integer> getRandomNumList(int numOfDice) {
        List<Integer> rand = new ArrayList<>();
        for(int i = 0; i < numOfDice; i++) {
            rand.add((int) Math.floor(Math.random()*6) +1);
        }
        return rand;
    }
}
