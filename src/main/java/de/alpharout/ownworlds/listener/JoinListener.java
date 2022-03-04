package de.alpharout.ownworlds.listener;

import de.alpharout.ownworlds.OwnWorlds;
import de.alpharout.ownworlds.api.ItemComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        joinEvent.getPlayer().getInventory().setItem(8, ItemComponent.getItemComponent("switcher").getItemStack());
    }
}
