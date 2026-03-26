package ru.Irina.NauJava.service;

import org.springframework.stereotype.Service;
import ru.Irina.NauJava.entity.Score;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Override
    public void createScore(Long id, String title, String composer,
                            String instrument, String genre, String difficulty) {
    }

    @Override
    public Score findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public void updateTitle(Long id, String newTitle) {
    }

    @Override
    public List<Score> findByComposer(String composer) {
        return List.of();
    }

    @Override
    public List<Score> findAll() {
        return List.of();
    }
}
