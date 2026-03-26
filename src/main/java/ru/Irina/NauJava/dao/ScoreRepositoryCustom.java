package ru.Irina.NauJava.dao;

import ru.Irina.NauJava.entity.Score;
import java.util.List;

public interface ScoreRepositoryCustom {


    List<Score> findByDifficultyAndTitle(String difficulty, String title);

    List<Score> findByComposerName(String composerName);
}
