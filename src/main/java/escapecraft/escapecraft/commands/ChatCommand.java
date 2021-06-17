package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.*;
import escapecraft.escapecraft.managers.ChatManager;

import java.util.ArrayList;
import java.util.List;

public class ChatCommand {

    public ChatCommand() {
        new CommandAPICommand("chat")
                .withPermission(CommandPermission.OP)
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
                )
                .register();
    }
}
