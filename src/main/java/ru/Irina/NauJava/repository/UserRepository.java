package ru.Irina.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.Irina.NauJava.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
