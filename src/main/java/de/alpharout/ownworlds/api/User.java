package de.alpharout.ownworlds.api;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {
    private static final HashMap<UUID, User> USERMAP = new HashMap<>();

    public static HashMap<UUID, User> getUserMap() {
        return USERMAP;
    }

    /*
    Bukkit Player, but with additional OwnWorlds information like Level.
     */
    private OfflinePlayer offlinePlayer;
    private ArrayList<Level> levels = new ArrayList<>();

    public User(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        levels = new ArrayList<>();
    }

    public boolean addLevel(Level level) {
        if (levels.contains(level)) {
            throw new IllegalArgumentException("Level was already in the levels list of the user.");
        }
        if (level == null) {
            throw new NullArgumentException("Level object is null.");
        }
        levels.add(level);
        return true;
    }

    public Level[] getLevels() {
        return levels.toArray(new Level[0]);
    }
}
