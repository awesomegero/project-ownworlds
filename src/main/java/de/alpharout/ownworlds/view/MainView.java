package de.alpharout.ownworlds.view;

import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.api.gui.View;

import java.util.HashMap;

public class MainView extends View {
    @Override
    public HashMap<Integer, ItemComponent> getComponentMap() {
        addComponent(12, ItemComponent.getItemComponent("switcher"));
        addComponent(14, ItemComponent.getItemComponent("visibility-toggler"));

        return super.getComponentMap();
    }
}
