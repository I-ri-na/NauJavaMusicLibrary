package ru.Irina.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.Irina.NauJava.repository.ScoreRepository;

@Controller
public class ScoreController {

    private final ScoreRepository scoreRepository;

    public ScoreController(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }


    @GetMapping("/ui/scores")
    public String getScoresPage(Model model) {
        model.addAttribute("scores", scoreRepository.findAll());
        return "scores";
    }
}