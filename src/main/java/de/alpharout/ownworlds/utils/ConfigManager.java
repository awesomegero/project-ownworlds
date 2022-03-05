package de.alpharout.ownworlds.utils;

import de.alpharout.ownworlds.OwnWorlds;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private File messageFile;
    private File databaseFile;
    private YamlConfiguration messageConf;
    private YamlConfiguration databaseConf;

    public void load() {
        messageFile = new File(OwnWorlds.getInstance().getDataFolder(), "message.yml");
        databaseFile = new File(OwnWorlds.getInstance().getDataFolder(), "database.yml");

        if (!messageFile.exists()) {
            OwnWorlds.getInstance().saveResource("message.yml", false);
        }
        if (!databaseFile.exists()) {
            OwnWorlds.getInstance().saveResource("database.yml", false);
        }
        messageConf = new YamlConfiguration();
        databaseConf = new YamlConfiguration();
        String currentFileName = "unknown";
        try {
            currentFileName = messageFile.getName();
            messageConf.load(messageFile);
            Log.debug("Loaded " + currentFileName + " configuration.");

            currentFileName = databaseFile.getName();
            databaseConf.load(databaseFile);
            Log.debug("Loaded " + currentFileName + " configuration.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getMessageFile() {
        return messageFile;
    }

    public File getDatabaseFile() {
        return databaseFile;
    }

    public YamlConfiguration getMessageConf() {
        return messageConf;
    }

    public YamlConfiguration getDatabaseConf() {
        return databaseConf;
    }
}
