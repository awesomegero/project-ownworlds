package de.alpharout.ownworlds;

import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.api.gui.View;
import de.alpharout.ownworlds.components.*;
import de.alpharout.ownworlds.listener.DropListener;
import de.alpharout.ownworlds.listener.InteractListener;
import de.alpharout.ownworlds.listener.InventoryClickListener;
import de.alpharout.ownworlds.listener.JoinListener;
import de.alpharout.ownworlds.utils.ConfigManager;
import de.alpharout.ownworlds.view.MainView;
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
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);

        ItemComponent.addComponent("switcher", new SwitcherComponent("switcher"));
        ItemComponent.addComponent("visibility-toggler", new VisibilityToggleComponent("visibility-toggler"));
        ItemComponent.addComponent("join-public-world", new JoinPublicWorldComponent("join-public-world"));
        ItemComponent.addComponent("join-private-world", new JoinPrivateWorldComponent("join-private-world"));
        ItemComponent.addComponent("join-own-world", new JoinOwnWorldComponent("join-own-world"));
        ItemComponent.addComponent("create-world", new CreateWorldComponent("create-world"));
        ItemComponent.addComponent("white-filler", new WhiteFillerComponent("white-filler"));

        View.addView("main", new MainView());
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
