package ru.Irina.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Irina.NauJava.entity.Favorite;

@RepositoryRestResource(path = "favorites")
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}