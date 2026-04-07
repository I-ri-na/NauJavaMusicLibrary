package ru.Irina.NauJava.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Irina.NauJava.entity.Role;
import ru.Irina.NauJava.entity.User;
import ru.Irina.NauJava.repository.UserRepository;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Возвращает страницу регистрации
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    // Возвращает страницу входа (логина)
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Принимает данные с формы регистрации
    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        try {
            // Проверяем, занят ли логин
            if (userRepository.findByLogin(user.getLogin()) != null) {
                model.addAttribute("message", "Пользователь с таким логином уже существует!");
                return "registration";
            }

            // Шифруем пароль перед сохранением в БД
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // Выдаем новому пользователю права обычного юзера
            user.setRole(Role.USER);

            userRepository.save(user);

            // Если все успешно — перенаправляем на страницу входа
            return "redirect:/login";
        } catch (Exception ex) {
            model.addAttribute("message", "Произошла ошибка при регистрации");
            ex.printStackTrace();
            return "registration";
        }
    }

}