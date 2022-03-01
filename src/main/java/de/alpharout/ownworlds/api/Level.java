package de.alpharout.ownworlds.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.UUID;

public class Level {
    /*
    Bukkit World, but with additional OwnWorlds information like owner.
     */
    private World world;
    private User user;
    private UUID uuid;

    /*
    UUID is not the identifier of the user but an own id.
     */
    public Level(UUID uuid, User user) {
        this.uuid = uuid;
    }

    public void create(World.Environment environment) {
        WorldCreator worldCreator = new WorldCreator(uuid.toString());
        worldCreator.environment(environment);
        world = worldCreator.createWorld();
    }

    public void load() {
        world = Bukkit.getWorld(uuid.toString());
    }

    public World getWorld() {
        return world;
    }

    public User getUser() {
        return user;
    }

    public UUID getUuid() {
        return uuid;
    }
}
