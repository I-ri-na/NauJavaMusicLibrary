package ru.Irina.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.Irina.NauJava.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
