package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import escapecraft.escapecraft.Escaperoom;
import escapecraft.escapecraft.QuizLoader;
import escapecraft.escapecraft.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.ArrayList;
import java.util.List;

public class StartCommand {

    public StartCommand() {
        List<Argument> arguments = new ArrayList<>();

        new CommandAPICommand("start")
                .withArguments(new GreedyStringArgument("quiz navn"))
                        .executesPlayer((player, args) -> {
                            System.out.println("start command");
                            if(!QuizLoader.quizExists((String) args[0])) return;

                            System.out.println("existing");

                            Escaperoom.getGamer(player).setAllQuestions(QuizLoader.getQuiz((String) args[0]));
                            Escaperoom.getGamer(player).reset();
                        })
                .register();
    }
}
