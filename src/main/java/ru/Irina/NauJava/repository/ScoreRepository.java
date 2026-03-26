package ru.Irina.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.Irina.NauJava.entity.Score;
import java.util.List;

public interface ScoreRepository extends CrudRepository<Score, Long> {

    // Метод 1: поиск по сложности и названию
    List<Score> findByDifficultyAndTitle(String difficulty, String title);

    // Метод 2: поиск партитур по имени композитора
    @Query("FROM Score s WHERE s.composer.name = :composerName")
    List<Score> findByComposerName(@Param("composerName") String composerName);
}
