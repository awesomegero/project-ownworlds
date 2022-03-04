package de.alpharout.ownworlds.components;

import de.alpharout.ownworlds.OwnWorlds;
import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.api.gui.Display;
import de.alpharout.ownworlds.api.gui.View;
import de.alpharout.ownworlds.utils.Log;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.geysermc.floodgate.api.FloodgateApi;

public class SwitcherComponent extends ItemComponent {

    public SwitcherComponent(String name) {
        super(name);

        this.typeName = OwnWorlds.getConfigManager().getMessageConf().getString("switcher.item");
        this.rawItemName = OwnWorlds.getConfigManager().getMessageConf().getString("switcher.name");

        if (rawItemName != null) this.itemName = ChatColor.translateAlternateColorCodes('&', rawItemName);
        else this.itemName = "§7» §cSwitcher";
        if (typeName != null) this.material = Material.getMaterial(typeName);
        else this.material = Material.ARROW;

    }

    @Override
    public ItemStack getItemStack(Player player) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void handleClick(PlayerInteractEvent interactEvent) {
        if (FloodgateApi.getInstance().isFloodgatePlayer(interactEvent.getPlayer().getUniqueId())) openBedrockGUI(interactEvent.getPlayer());
        else openJavaGUI(interactEvent.getPlayer());
        Log.debug("Received right-click on switcher.");
    }

    @Override
    public void handleInventoryClick(InventoryClickEvent clickEvent) {
        clickEvent.setCancelled(true);
    }

    @Override
    public void handleDrop(PlayerDropItemEvent dropItemEvent) {
        dropItemEvent.setCancelled(true);
    }

    private void openJavaGUI(Player player) {
        Display display = new Display(player, "§7» §cSwitcher", 3*9);
        display.setView(View.getView("main"));
        player.openInventory(display.getInventory());
    }

    private void openBedrockGUI(Player player) {
        player.sendMessage("§cThe Bedrock GUI is not implemented yet!");
    }
}
