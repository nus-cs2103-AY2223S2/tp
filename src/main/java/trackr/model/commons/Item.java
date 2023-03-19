package trackr.model.commons;

/**
 * Generic class that all items extend from.
 */
public abstract class Item {
    protected String itemType;

    Item(String itemType) {
        this.itemType = itemType;
    }
}
