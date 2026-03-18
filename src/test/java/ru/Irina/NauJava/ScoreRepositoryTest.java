package ru.Irina.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Irina.NauJava.dao.ScoreRepositoryCustom;
import ru.Irina.NauJava.entity.Composer;
import ru.Irina.NauJava.entity.Score;
import ru.Irina.NauJava.repository.ComposerRepository;
import ru.Irina.NauJava.repository.ScoreRepository;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class ScoreRepositoryTest {

    private final ScoreRepository scoreRepository;
    private final ScoreRepositoryCustom scoreRepositoryCustom;
    private final ComposerRepository composerRepository;

    @Autowired
    ScoreRepositoryTest(ScoreRepository scoreRepository,
                        ScoreRepositoryCustom scoreRepositoryCustom,
                        ComposerRepository composerRepository) {
        this.scoreRepository = scoreRepository;
        this.scoreRepositoryCustom = scoreRepositoryCustom;
        this.composerRepository = composerRepository;
    }

    @Test
    void testFindByDifficultyAndTitle() {
        String title = UUID.randomUUID().toString();
        String difficulty = "Advanced";

        Score score = new Score();
        score.setTitle(title);
        score.setDifficulty(difficulty);
        scoreRepository.save(score);

        // тест Query Method
        List<Score> result = scoreRepository.findByDifficultyAndTitle(difficulty, title);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(title, result.getFirst().getTitle());

        // тест Criteria API
        List<Score> resultCriteria = scoreRepositoryCustom.findByDifficultyAndTitle(difficulty, title);
        Assertions.assertFalse(resultCriteria.isEmpty());
        Assertions.assertEquals(title, resultCriteria.getFirst().getTitle());
    }

    @Test
    void testFindByComposerName() {
        String composerName = UUID.randomUUID().toString();

        Composer composer = new Composer();
        composer.setName(composerName);
        composerRepository.save(composer);

        Score score = new Score();
        score.setTitle(UUID.randomUUID().toString());
        score.setComposer(composer);
        scoreRepository.save(score);

        // тест @Query (JPQL)
        List<Score> result = scoreRepository.findByComposerName(composerName);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(composerName, result.getFirst().getComposer().getName());

        // тест Criteria API
        List<Score> resultCriteria = scoreRepositoryCustom.findByComposerName(composerName);
        Assertions.assertFalse(resultCriteria.isEmpty());
        Assertions.assertEquals(composerName, resultCriteria.getFirst().getComposer().getName());
    }
}
