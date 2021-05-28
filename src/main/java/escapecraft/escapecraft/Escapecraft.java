package escapecraft.escapecraft;

import escapecraft.escapecraft.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import static escapecraft.escapecraft.Easy.broadcast;


public final class Escapecraft extends JavaPlugin implements Listener {

    public void onEnable() {
        // Plugin startup logic
        new ChatCommand();
        new PartyCommands();

        Bukkit.getPluginManager().registerEvents(new ChatManager(), this);

        this.getCommand("quest").setExecutor(new SendQuestionCommand());
    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}