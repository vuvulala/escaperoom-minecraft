package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import escapecraft.escapecraft.managers.ChatManager;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class DebugCommand implements Listener {
    public DebugCommand() {
        List<Argument> arguments = new ArrayList<>();

        new CommandAPICommand("debug")
                .withSubcommand(new CommandAPICommand("chat")
                .withSubcommand(new CommandAPICommand("enable")
                        .executes((sender, args) -> {
                            ChatManager.chatEnabled = true;
                            sender.sendMessage("Chat enabled!");
                        })
                )
                .withSubcommand(new CommandAPICommand("disable")
                        .executes((sender, args) -> {
                            ChatManager.chatEnabled = false;
                            sender.sendMessage("Chat disabled!");
                        })
                ))
                .register();
    }

}
