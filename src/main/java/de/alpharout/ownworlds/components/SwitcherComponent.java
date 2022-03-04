package de.alpharout.ownworlds.components;

import de.alpharout.ownworlds.OwnWorlds;
import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.utils.Log;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SwitcherComponent extends ItemComponent {
    private String typeName;
    private String rawItemName;
    private String itemName;
    private Material material;

    public SwitcherComponent() {
        this.typeName = OwnWorlds.getConfigManager().getMessageConf().getString("switcher.item");
        this.rawItemName = OwnWorlds.getConfigManager().getMessageConf().getString("switcher.name");

        if (rawItemName != null) this.itemName = ChatColor.translateAlternateColorCodes('&', rawItemName);
        else this.itemName = "§7» §cSwitcher";
        if (typeName != null) this.material = Material.getMaterial(typeName);
        else this.material = Material.ARROW;
    }

    @Override
    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void handleRightClick(PlayerInteractEvent interactEvent) {
        Log.debug("Received right-click on switcher.");
    }
}
