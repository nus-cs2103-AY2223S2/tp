package trackr.model.item;

/**
 * Generic class that all items extend from.
 */
public abstract class Item {
    protected String itemType;

    Item(String itemType) {
        this.itemType = itemType;
    }

    public abstract boolean isSameItem(Item otherItem);
}
