package de.alpharout.ownworlds;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.api.gui.View;
import de.alpharout.ownworlds.components.*;
import de.alpharout.ownworlds.listener.DropListener;
import de.alpharout.ownworlds.listener.InteractListener;
import de.alpharout.ownworlds.listener.InventoryClickListener;
import de.alpharout.ownworlds.listener.JoinListener;
import de.alpharout.ownworlds.utils.ConfigManager;
import de.alpharout.ownworlds.utils.DatabaseManager;
import de.alpharout.ownworlds.utils.Log;
import de.alpharout.ownworlds.view.MainView;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class OwnWorlds extends JavaPlugin implements PluginMessageListener {

    private static OwnWorlds PLUGIN;
    private static ConfigManager CONFIGMANAGER;
    private static DatabaseManager DATABASEMANAGER;
    private static boolean DEBUG;
    private static String CURRENT_SERVER_NAME;

    @Override
    public void onEnable() {
        PLUGIN = this;

        saveDefaultConfig();
        CONFIGMANAGER = new ConfigManager();
        CONFIGMANAGER.load();
        DATABASEMANAGER = new DatabaseManager(
                CONFIGMANAGER.getDatabaseConf().getString("hostname"),
                CONFIGMANAGER.getDatabaseConf().getInt("port"),
                CONFIGMANAGER.getDatabaseConf().getString("database"),
                CONFIGMANAGER.getDatabaseConf().getString("username"),
                CONFIGMANAGER.getDatabaseConf().getString("password")
        );
        DEBUG = getConfig().getBoolean("debug-mode");

        if (!this.getServer().getPluginManager().isPluginEnabled("floodgate")) {
            Log.error("Floodgate is not installed on this server. Please install it!");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        DATABASEMANAGER.connect();

        //BungeeCord Stuff
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        //The BungeeCord name of the current server
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServer");
        this.getServer().sendPluginMessage(this, "BungeeCord", out.toByteArray());

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

    public static DatabaseManager getDatabaseManager() {
        return DATABASEMANAGER;
    }

    public static String getCurrentServerName() {
        return CURRENT_SERVER_NAME;
    }

    public static boolean isDebug() {
        return DEBUG;
    }





    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        ByteArrayDataInput in = ByteStreams.newDataInput(message);

        if(channel.equals("BungeeCord")){

            String subChannel = in.readUTF();

            if(subChannel.equals("GetServer")){
                CURRENT_SERVER_NAME = in.readUTF();

                //Instantly after retrieving the server name, the server gets registered via database
                DATABASEMANAGER.registerServer(CURRENT_SERVER_NAME);
            }

        }

    }
}
