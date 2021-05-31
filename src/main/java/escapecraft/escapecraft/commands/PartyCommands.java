package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import escapecraft.escapecraft.managers.PartyManager;
import escapecraft.escapecraft.managers.QuestionManager;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Collection;

public class PartyCommands {
    public PartyCommands() {
        //commands with args
        new CommandAPICommand("party")
                .withSubcommand(new CommandAPICommand("create")
                        .withArguments(new TextArgument("Party name"))
                        .withArguments(new EntitySelectorArgument("Members", EntitySelectorArgument.EntitySelector.MANY_PLAYERS))
                        .executes((player, args) -> {
                            String partyName = (String) args[0];

                            if(PartyManager.has(partyName)) {
                                player.sendMessage("That party already exists!");
                                return;
                            }

                            PartyManager.createParty(partyName, player, (Collection<Player>) args[1]);
                            player.sendMessage("Created party: " + partyName);
                        })
                )
                .withSubcommand(new CommandAPICommand("disband")
                        .withArguments(new TextArgument("party").overrideSuggestions((sender -> PartyManager.getAllAsString())))
                        .executes((player, args) -> {
                            if(!PartyManager.has((String) args[0])) {
                                player.sendMessage("Party does not exist!");
                                return;
                            }
                            PartyManager.disband((String) args[0]);
                            player.sendMessage("Disbanded party: " + args[0]);
                        })
                )
                .withSubcommand(new CommandAPICommand("list")
                        .withArguments(new TextArgument("party").overrideSuggestions((sender -> PartyManager.getAllAsString())))
                        .executes((player, args) -> {
                            if(!PartyManager.has((String) args[0])) return;

                            ArrayList<String> members = PartyManager.get((String) args[0]).getMembersAsStringArray();
                            for(String currentName : members) player.sendMessage(currentName);
                        })
                )
                .withSubcommand(new CommandAPICommand("message")
                        .withArguments(new TextArgument("party").overrideSuggestions((sender -> PartyManager.getAllAsString())))
                        .withArguments(new GreedyStringArgument("message"))
                        .executes((player, args) -> {
                            if (! PartyManager.has((String) args[0])) return;

                            PartyManager.get((String) args[0]).sendMessage((String) args[1]);
                        })
                )
                .withSubcommand(new CommandAPICommand("add")
                        .withArguments(new TextArgument("party").overrideSuggestions((sender -> PartyManager.getAllAsString())))
                        .withArguments(new EntitySelectorArgument("player(s)", EntitySelectorArgument.EntitySelector.MANY_PLAYERS))
                        .executes((player, args) -> {
                            if(! PartyManager.has((String) args[0])) return;

                            PartyManager.get((String) args[0]).addPlayers((Collection<Player>) args[1]);
                        })
                )
                .withSubcommand(new CommandAPICommand("remove")
                        .withArguments(new TextArgument("party").overrideSuggestions((sender -> PartyManager.getAllAsString())))
                        .withArguments(new EntitySelectorArgument("player(s)", EntitySelectorArgument.EntitySelector.MANY_PLAYERS))
                        .executes((player, args) -> {
                            if(! PartyManager.has((String) args[0])) return;

                            PartyManager.get((String) args[0]).removePlayers((Collection<Player>) args[1]);
                        })
                )
                .withSubcommand(new CommandAPICommand("start")
                        .withArguments(new TextArgument("party").overrideSuggestions((sender -> PartyManager.getAllAsString())))
                        .executes((player, args) -> {
                            if(! PartyManager.has((String) args[0])) return;

                            if(PartyManager.get((String) args[0]).start()) {

                            } else {
                                player.sendMessage("party can't start quiz");
                            }
                        })
                )
                .withSubcommand(new CommandAPICommand("assign")
                        .withArguments(new TextArgument("party").overrideSuggestions((sender -> PartyManager.getAllAsString())))
                        .withArguments(new TextArgument("quiz"))
                        .executes((player, args) -> {
                            if(! QuestionManager.questionExists((String) args[1])) return;
                            if(! PartyManager.has((String) args[0])) return;

                            PartyManager.get((String) args[0]).assignQuiz((String) args[1]);
                            System.out.println("assigned quiz");
                        })
                )
                .register();

        //commands without args
        new CommandAPICommand("party")
                .withSubcommand(new CommandAPICommand("create")
                        .executes((player, args) -> {
                            player.sendMessage("Creates a party with a specified name and members");
                        })
                )
                .withSubcommand(new CommandAPICommand("disband")
                        .executes((player, args) -> {
                            player.sendMessage("Deletes a party with a given name");
                        })
                )
                .withSubcommand(new CommandAPICommand("list")
                        .executes((player, args) -> {
                            PartyManager.parties.forEach((k, v) -> player.sendMessage(k));
                        })
                )
                .withSubcommand(new CommandAPICommand("message")
                        .executes((player, args) -> {
                            player.sendMessage("sends a message to everyone in a party");
                        })
                )
                .register();
    }
}
