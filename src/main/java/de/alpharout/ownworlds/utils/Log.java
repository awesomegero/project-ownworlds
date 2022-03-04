package de.alpharout.ownworlds.utils;

import de.alpharout.ownworlds.OwnWorlds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Log {
    public static void info(String msg) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "OwnWorlds | " + msg);
    }

    public static void error(String msg) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "OwnWorlds | " + msg);
    }

    public static void debug(String msg) {
        if (OwnWorlds.isDebug()) Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "OwnWorlds | " + msg);
    }
}
