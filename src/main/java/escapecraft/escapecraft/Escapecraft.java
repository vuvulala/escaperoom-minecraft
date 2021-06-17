package escapecraft.escapecraft;

import escapecraft.escapecraft.commands.*;
import escapecraft.escapecraft.events.CheckAnswerEvent;
import escapecraft.escapecraft.events.EscaperoomEvents;
import escapecraft.escapecraft.events.SignPlaceholders;
import escapecraft.escapecraft.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Escapecraft extends JavaPlugin implements Listener {

    private final String testString = "Dette er en test!";

    public void onEnable() {
        // Plugin startup logic
        new ChatCommand();
        new StartCommand();
        new DebugCommand();
        new EscaperoomCommand();

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new DebugCommand(), this);
        Bukkit.getPluginManager().registerEvents(new ChatManager(), this);
        Bukkit.getPluginManager().registerEvents(new CheckAnswerEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EscaperoomEvents(), this);
        new SignPlaceholders();

    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}