package ru.Irina.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Irina.NauJava.entity.Composer;

@RepositoryRestResource(path = "composers")
public interface ComposerRepository extends JpaRepository<Composer, Long> {
}