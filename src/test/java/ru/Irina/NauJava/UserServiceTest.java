package ru.Irina.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Irina.NauJava.entity.Favorite;
import ru.Irina.NauJava.entity.Score;
import ru.Irina.NauJava.entity.User;
import ru.Irina.NauJava.repository.FavoriteRepository;
import ru.Irina.NauJava.repository.ScoreRepository;
import ru.Irina.NauJava.repository.UserRepository;
import ru.Irina.NauJava.service.UserService;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final ScoreRepository scoreRepository;

    @Autowired
    UserServiceTest(UserService userService,
                    UserRepository userRepository,
                    FavoriteRepository favoriteRepository,
                    ScoreRepository scoreRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.scoreRepository = scoreRepository;
    }

    @Test
    void testDeleteUserWithFavorites() {
        // создаём партитуру (нужна для Favorite)
        Score score = new Score();
        score.setTitle(UUID.randomUUID().toString());
        scoreRepository.save(score);

        // создаём пользователя
        User user = new User();
        user.setName(UUID.randomUUID().toString());
        user.setLogin(UUID.randomUUID().toString());
        userRepository.save(user);

        // создаём избранное с пользователем и партитурой
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setScore(score);
        favorite.setRating(5);
        favoriteRepository.save(favorite);

        // удаляем пользователя вместе с избранным
        userService.deleteUserWithFavorites(user.getId());

        // проверяем что пользователь удалён
        Optional<User> foundUser = userRepository.findById(user.getId());
        Assertions.assertTrue(foundUser.isEmpty());

        // проверяем что избранное удалено
        Optional<Favorite> foundFavorite = favoriteRepository.findById(favorite.getId());
        Assertions.assertTrue(foundFavorite.isEmpty());
    }
}
