package ru.Irina.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.Irina.NauJava.entity.Favorite;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
}
