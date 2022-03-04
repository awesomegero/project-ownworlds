package de.alpharout.ownworlds.api;

import de.alpharout.ownworlds.OwnWorlds;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ItemComponent {
    private static final HashMap<String, ItemComponent> COMPONENTMAP = new HashMap<>();

    public static void addComponent(String name, ItemComponent component) {
        COMPONENTMAP.put(name, component);
    }

    public static ItemComponent getItemComponent(String name) {
        return COMPONENTMAP.get(name);
    }

    public static ItemComponent getItemComponentByDisplayName(Player player, String displayName) {
        Predicate<ItemComponent> byDisplayName = component -> component.getItemStack(player).getItemMeta().getDisplayName().equals(displayName);
        ItemComponent itemComponent;
        if (COMPONENTMAP.values().stream().anyMatch(byDisplayName)) itemComponent = COMPONENTMAP.values().stream().filter(byDisplayName).findFirst().get();
        else return null;
        return itemComponent;
    }

    protected String typeName;
    protected String rawItemName;
    protected String itemName;
    protected Material material;

    public ItemComponent(String name) {
        this.typeName = OwnWorlds.getConfigManager().getMessageConf().getString(name + ".item");
        this.rawItemName = OwnWorlds.getConfigManager().getMessageConf().getString(name + ".name");

        if (rawItemName != null) this.itemName = ChatColor.translateAlternateColorCodes('&', rawItemName);
        else this.itemName = name;
        if (typeName != null) this.material = Material.getMaterial(typeName);
        else this.material = Material.GLASS;
    }

    public ItemStack getItemStack(Player player) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public void handleInventoryClick(InventoryClickEvent clickEvent) {
    }

    public void handleClick(PlayerInteractEvent interactEvent) {
    }

    public void handleDrop(PlayerDropItemEvent dropItemEvent) {

    }
}
