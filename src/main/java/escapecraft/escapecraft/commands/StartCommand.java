package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import escapecraft.escapecraft.managers.Escaperoom;
import escapecraft.escapecraft.classes.Gamer;
import escapecraft.escapecraft.managers.QuizLoader;

public class StartCommand {

    public StartCommand() {
        new CommandAPICommand("start")
                .withArguments(new GreedyStringArgument("quiz navn"))
                .executesPlayer((player, args) -> {
                    if(!QuizLoader.quizExists((String) args[0])) {
                        player.sendMessage("that escaperoom doesn't exist!");
                        return;
                    }

                    Gamer gamer = Escaperoom.getGamer(player);
                    gamer.setAllQuestions(QuizLoader.getQuiz((String) args[0]));
                    gamer.startQuiz();
                })
                .register();
    }
}