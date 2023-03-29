package seedu.address.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an inventory, belonging to an Entity.
 */
public class Inventory {
    private final List<Item> items = new ArrayList<>();

    // Default constructor
    public Inventory() {
    }

    /**
     * Given a list of items, initialize inventory.
     */
    public Inventory(List<Item> items) {
        this.items.addAll(items);
    }

    /**
     * Returns an empty inventory.
     */
    public static Inventory emptyInventory() {
        return new Inventory();
    }

    /**
     * Add item to the inventory
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Delete item from the inventory
     */
    public void deleteItem(Item item) {
        items.remove(item);
    }

    /**
     * Returns unmodifiable list of items contained by the inventory.
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

}
