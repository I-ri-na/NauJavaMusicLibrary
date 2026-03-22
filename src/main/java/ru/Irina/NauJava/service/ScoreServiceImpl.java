package ru.Irina.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Irina.NauJava.entity.Score;
import ru.Irina.NauJava.repository.ScoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void createScore(Long id, String title, String composer,
                            String instrument, String genre, String difficulty) {
        Score score = new Score();
        score.setId(id);
        score.setTitle(title);
        score.setComposer(composer);
        score.setInstrument(instrument);
        score.setGenre(genre);
        score.setDifficulty(difficulty);
        scoreRepository.create(score);
    }

    @Override
    public Score findById(Long id) {
        return scoreRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        scoreRepository.delete(id);
    }

    @Override
    public void updateTitle(Long id, String newTitle) {
        Score score = scoreRepository.read(id);
        if (score != null) {
            score.setTitle(newTitle);
            scoreRepository.update(score);
        }
    }

    @Override
    public List<Score> findByComposer(String composer) {
        return scoreRepository.findAll().stream()
                .filter(s -> s.getComposer().equalsIgnoreCase(composer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Score> findAll() {
        return scoreRepository.findAll();
    }
}
