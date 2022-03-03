package de.alpharout.ownworlds.listener;

import de.alpharout.ownworlds.OwnWorlds;
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
        // TODO: Move to external class
        {
            String typeName = OwnWorlds.getConfigManager().getMessageConf().getString("switcher.item");
            if (typeName == null) return;
            String rawItemName = OwnWorlds.getConfigManager().getMessageConf().getString("switcher.name");
            if (rawItemName == null) return;
            String itemName = ChatColor.translateAlternateColorCodes('&', rawItemName);
            Material material = Material.getMaterial(typeName);
            if (material == null) return;

            ItemStack switcherItemStack = new ItemStack(material);
            ItemMeta switcherMeta = switcherItemStack.getItemMeta();
            switcherMeta.setDisplayName(itemName);
            switcherItemStack.setItemMeta(switcherMeta);

            joinEvent.getPlayer().getInventory().setItem(8, switcherItemStack);
        }
    }
}
