package me.suprime.newyearplugin;

import lombok.Getter;
import me.suprime.newyearplugin.presents.PresentsManager;
import me.suprime.newyearplugin.presents.listener.PresentsListener;
import me.suprime.newyearplugin.snow.command.SnowCommand;
import me.suprime.newyearplugin.task.Task;
import me.suprime.newyearplugin.util.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;

public final class NewYearPlugin extends JavaPlugin {

    @Getter
    private static NewYearPlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        registerCommands();
        registerListener();
        new Task().start();

        // TODO: Вам задание - реализовать добавление подарков

        List<Location> list = new ArrayList<>();

        list.add(new Location(Bukkit.getWorld("world"), 223, 74, 151));
        list.add(LocationUtil.stringLocation("world; 222; 73; 154"));
        list.add(LocationUtil.stringLocation("world; 239; 73; 147"));
        list.add(LocationUtil.stringLocation("world; 220; 71; 152"));
        list.add(LocationUtil.stringLocation("world; 222; 72; 160"));

        PresentsManager.INSTANCE.loadPresents(list);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        getCommand("snow").setExecutor(new SnowCommand());
    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new PresentsListener(), this);
    }
}
