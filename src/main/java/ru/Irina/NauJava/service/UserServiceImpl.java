package ru.Irina.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.Irina.NauJava.repository.FavoriteRepository;
import ru.Irina.NauJava.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           FavoriteRepository favoriteRepository,
                           PlatformTransactionManager transactionManager) {
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteUserWithFavorites(Long userId) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            // удаляем все избранные партитуры пользователя
            favoriteRepository.findAll().forEach(favorite -> {
                if (favorite.getUser().getId().equals(userId)) {
                    favoriteRepository.delete(favorite);
                }
            });

            // удаляем самого пользователя
            userRepository.deleteById(userId);

            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
