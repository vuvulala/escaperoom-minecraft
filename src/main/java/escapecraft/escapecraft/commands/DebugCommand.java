package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import escapecraft.escapecraft.managers.Escaperoom;
import escapecraft.escapecraft.classes.Gamer;
import org.bukkit.event.Listener;

public class DebugCommand implements Listener {
    public DebugCommand() {
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
