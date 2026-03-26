package ru.Irina.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Irina.NauJava.entity.Score;
import java.util.List;

@RepositoryRestResource(path = "scores")
public interface ScoreRepository extends JpaRepository<Score, Long> {

    // Метод 1: поиск по сложности и названию
    List<Score> findByDifficultyAndTitle(@Param("difficulty") String difficulty, @Param("title") String title);

    // Метод 2: поиск партитур по имени композитора
    @Query("FROM Score s WHERE s.composer.name = :composerName")
    List<Score> findByComposerName(@Param("composerName") String composerName);
}