package escapecraft.escapecraft.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import escapecraft.escapecraft.ChatManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatCommand {
    private CommandAPICommand command;

    public ChatCommand() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new TextArgument("").overrideSuggestions("enable", "disable"));

        new CommandAPICommand("chat")
                .withArguments(arguments)
                .executesPlayer((player, args) -> {
                    switch ((String) args[0]) {
                        case "enable": {
                            ChatManager.chatEnabled = true;
                            player.sendMessage("Chat enabled!");
                            return;
                        }
                        case "disable": {
                            ChatManager.chatEnabled = false;
                            player.sendMessage("Chat disabled!");
                            return;
                        }
                        case "ist_ein_schnitzel": {
                            player.sendMessage("Es ist sehr gut, ja.");
                        }
                        default: {
                            player.sendMessage("Invalid option!");
                            return;
                        }
                    }
                }).register();
    }
}
