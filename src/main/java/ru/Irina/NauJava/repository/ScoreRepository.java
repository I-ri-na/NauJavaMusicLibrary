package ru.Irina.NauJava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Irina.NauJava.entity.Score;

import java.util.List;

@Component
public class ScoreRepository implements CrudRepository<Score, Long> {

    private final List<Score> scoreContainer;

    @Autowired
    public ScoreRepository(List<Score> scoreContainer) {
        this.scoreContainer = scoreContainer;
    }

    @Override
    public void create(Score score) {
        scoreContainer.add(score);
    }

    @Override
    public Score read(Long id) {
        return scoreContainer.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Score score) {
        for (int i = 0; i < scoreContainer.size(); i++) {
            if (scoreContainer.get(i).getId().equals(score.getId())) {
                scoreContainer.set(i, score);
                return;
            }
        }
    }

    @Override
    public void delete(Long id) {
        scoreContainer.removeIf(s -> s.getId().equals(id));
    }

    public List<Score> findAll() {
        return scoreContainer;
    }
}
