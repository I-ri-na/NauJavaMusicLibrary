package ru.Irina.NauJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.Irina.NauJava.entity.Composer;
import ru.Irina.NauJava.entity.Score;
import java.util.List;

@Repository
public class ScoreRepositoryImpl implements ScoreRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public ScoreRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Score> findByDifficultyAndTitle(String difficulty, String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Score> cq = cb.createQuery(Score.class);
        Root<Score> root = cq.from(Score.class);

        Predicate difficultyPredicate = cb.equal(root.get("difficulty"), difficulty);
        Predicate titlePredicate = cb.equal(root.get("title"), title);

        cq.select(root).where(cb.and(difficultyPredicate, titlePredicate));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Score> findByComposerName(String composerName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Score> cq = cb.createQuery(Score.class);
        Root<Score> root = cq.from(Score.class);

        Join<Score, Composer> composer = root.join("composer", JoinType.INNER);
        Predicate namePredicate = cb.equal(composer.get("name"), composerName);

        cq.select(root).where(namePredicate);
        return entityManager.createQuery(cq).getResultList();
    }
}
