package trackr.model.item;

import trackr.model.ModelEnum;

/**
 * Generic class that all items extend from.
 */
public abstract class Item {
    protected ModelEnum modelEnum;

    public Item(ModelEnum modelEnum) {
        this.modelEnum = modelEnum;
    }

    public abstract boolean isSameItem(Item otherItem);

    public ModelEnum getModelEnum() {
        return modelEnum;
    }
}
