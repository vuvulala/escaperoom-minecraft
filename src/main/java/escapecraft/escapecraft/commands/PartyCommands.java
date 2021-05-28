package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import escapecraft.escapecraft.PartyManager;
import org.bukkit.Bukkit;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PartyCommands {
    public PartyCommands() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new TextArgument("").overrideSuggestions("create"));
        arguments.add(new TextArgument("Party name"));
        arguments.add(new EntitySelectorArgument("Members", EntitySelectorArgument.EntitySelector.MANY_PLAYERS));

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
                            player.sendMessage("Disbanded party: " + (String) args[0]);
                        })
                )
                .withSubcommand(new CommandAPICommand("list")
                        .withArguments(new TextArgument("party").overrideSuggestions((sender -> PartyManager.getAllAsString())))
                        .executes((player, args) -> {
                            PartyManager.parties.forEach((k, v) -> {player.sendMessage(k);});
                        })
                )
                .register();

        //commands without args
        new CommandAPICommand("party")
                .withSubcommand(new CommandAPICommand("create")
                        .executes((player, args) -> {
                            Bukkit.getPlayer(player.getName()).performCommand("/help party create");
                        })
                )
                .withSubcommand(new CommandAPICommand("disband")
                        .executes((player, args) -> {
                            Bukkit.getPlayer(player.getName()).performCommand("/help party disband");
                        })
                )
                .withSubcommand(new CommandAPICommand("list")
                        .executes((player, args) -> {
                            PartyManager.parties.forEach((k, v) -> {
                                player.sendMessage(k);
                            });
                        })
                )
                .register();
    }
}
