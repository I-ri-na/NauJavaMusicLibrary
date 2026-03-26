package ru.Irina.NauJava.controller;

import org.springframework.web.bind.annotation.*;
import ru.Irina.NauJava.entity.Score;
import ru.Irina.NauJava.repository.ScoreRepository;
import ru.Irina.NauJava.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/custom/scores")
public class ScoreRestController {

    private final ScoreRepository scoreRepository;

    public ScoreRestController(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }


    @GetMapping("/{id}")
    public Score getScoreById(@PathVariable Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Партитура с ID " + id + " не найдена в базе данных"));
    }


    @GetMapping("/search")
    public List<Score> searchScores(@RequestParam String difficulty, @RequestParam String title) {
        List<Score> scores = scoreRepository.findByDifficultyAndTitle(difficulty, title);
        if (scores.isEmpty()) {
            throw new ResourceNotFoundException("Партитуры с такой сложностью и названием не найдены");
        }
        return scores;
    }
}