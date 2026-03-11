package ru.Irina.NauJava.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Irina.NauJava.entity.Score;
import ru.Irina.NauJava.service.ScoreService;

import java.util.List;

@Component
public class CommandProcessor {

    private final ScoreService scoreService;

    @Autowired
    public CommandProcessor(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split(" ");
        switch (cmd[0]) {
            case "create" -> {
                // create <id> <title> <composer> <instrument> <genre> <difficulty>
                scoreService.createScore(Long.valueOf(cmd[1]), cmd[2],
                        cmd[3], cmd[4], cmd[5], cmd[6]);
                System.out.println("Партитура добавлена.");
            }
            case "find" -> {
                // find <id>
                Score s = scoreService.findById(Long.valueOf(cmd[1]));
                System.out.println(s != null ? s : "Не найдено.");
            }
            case "delete" -> {
                // delete <id>
                scoreService.deleteById(Long.valueOf(cmd[1]));
                System.out.println("Удалено.");
            }
            case "update" -> {
                // update <id> <newTitle>
                scoreService.updateTitle(Long.valueOf(cmd[1]), cmd[2]);
                System.out.println("Название обновлено.");
            }
            case "list" -> {
                List<Score> all = scoreService.findAll();
                if (all.isEmpty()) System.out.println("Список пуст.");
                else all.forEach(System.out::println);
            }
            case "bycomposer" -> {
                // bycomposer <composer>
                scoreService.findByComposer(cmd[1]).forEach(System.out::println);
            }
            default -> System.out.println("Неизвестная команда.");
        }
    }
}
