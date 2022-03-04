package de.alpharout.ownworlds;

import de.alpharout.ownworlds.listener.DropListener;
import de.alpharout.ownworlds.listener.JoinListener;
import de.alpharout.ownworlds.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OwnWorlds extends JavaPlugin {
    private static OwnWorlds PLUGIN;
    private static ConfigManager CONFIGMANAGER;
    private static boolean DEBUG;

    @Override
    public void onEnable() {
        PLUGIN = this;
        saveDefaultConfig();
        CONFIGMANAGER = new ConfigManager();
        DEBUG = getConfig().getBoolean("debug-mode");

        CONFIGMANAGER.load();

        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new DropListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static OwnWorlds getInstance() {
        return PLUGIN;
    }

    public static ConfigManager getConfigManager() {
        return CONFIGMANAGER;
    }

    public static boolean isDebug() {
        return DEBUG;
    }
}
