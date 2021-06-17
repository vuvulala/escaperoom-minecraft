package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.Argument;
import escapecraft.escapecraft.Escaperoom;
import escapecraft.escapecraft.Gamer;
import escapecraft.escapecraft.managers.ChatManager;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Debug implements Listener {
    public Debug() {
        List<Argument> arguments = new ArrayList<>();

        new CommandAPICommand("debug")
            .withPermission(CommandPermission.OP)
            .withSubcommand(new CommandAPICommand("gamer")
                    .withSubcommand(new CommandAPICommand("list"))
                .executes((sender, args) -> {
                    for (Gamer gamer : Escaperoom.getGamers().values()) {
                        sender.sendMessage("Gamerboi: " + gamer.getPlayer().getName());
                    }
                }))
            .register();
    }
}
