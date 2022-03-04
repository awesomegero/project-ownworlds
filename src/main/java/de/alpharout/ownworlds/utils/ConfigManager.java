package de.alpharout.ownworlds.utils;

import de.alpharout.ownworlds.OwnWorlds;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private File messageFile;
    private YamlConfiguration messageConf;

    public void load() {
        messageFile = new File(OwnWorlds.getInstance().getDataFolder(), "message.yml");

        if (!messageFile.exists()) {
            OwnWorlds.getInstance().saveResource("message.yml", false);
        }
        messageConf = new YamlConfiguration();
        try {
            messageConf.load(messageFile);
            Log.debug("Loaded " + messageFile.getName() + " configuration.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getMessageFile() {
        return messageFile;
    }

    public YamlConfiguration getMessageConf() {
        return messageConf;
    }
}
