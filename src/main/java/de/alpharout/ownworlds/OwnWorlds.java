package de.alpharout.ownworlds;

import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.components.SwitcherComponent;
import de.alpharout.ownworlds.listener.DropListener;
import de.alpharout.ownworlds.listener.InteractListener;
import de.alpharout.ownworlds.listener.JoinListener;
import de.alpharout.ownworlds.utils.ConfigManager;
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
        this.getServer().getPluginManager().registerEvents(new InteractListener(), this);

        ItemComponent.addComponent("switcher", new SwitcherComponent());
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
