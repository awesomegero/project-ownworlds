package de.alpharout.ownworlds.api;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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

    public static ItemComponent getItemComponentByDisplayName(String displayName) {
        Predicate<ItemComponent> byDisplayName = component -> component.getItemStack().getItemMeta().getDisplayName().equals(displayName);
        ItemComponent itemComponent;
        if (COMPONENTMAP.values().stream().anyMatch(byDisplayName)) itemComponent = COMPONENTMAP.values().stream().filter(byDisplayName).findFirst().get();
        else return null;
        return itemComponent;
    }

    public ItemComponent() {

    }

    public ItemStack getItemStack() {
        return null;
    }

    public void handleInventoryClick(InventoryClickEvent clickEvent) {
    }

    public void handleRightClick(PlayerInteractEvent interactEvent) {
    }
}
