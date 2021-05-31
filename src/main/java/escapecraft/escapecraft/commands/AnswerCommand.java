package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import escapecraft.escapecraft.managers.PartyManager;

public class AnswerCommand {
    public AnswerCommand() {
        new CommandAPICommand("answer")
                .withArguments(new TextArgument("party name"))
                .withArguments(new IntegerArgument("question number"))
                .withArguments(new IntegerArgument("answer choice"))
                .withArguments(new BooleanArgument("correctness"))
                .executesPlayer((player, args) -> {
                    if(! PartyManager.has((String) args[0])) return;

                    PartyManager.get((String) args[0]).answer(player, (Integer) args[1], (Integer) args[2], (Boolean) args[3]);
                })
                .register();
    }
}
