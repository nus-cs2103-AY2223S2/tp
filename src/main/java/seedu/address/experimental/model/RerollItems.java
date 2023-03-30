package seedu.address.experimental.model;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Abstraction of all items
 */
public class RerollItems implements ReadOnlyEntities {

    private final ObservableList<Entity> items;

    /**
     * Initialize with list of items.
     * @param items Unmodifiable view of items
     */
    public RerollItems(ObservableList<Entity> items) {
        this.items = items;
    }

    @Override
    public ObservableList<Entity> getEntityList() {
        return items;
    }


    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollItems
                && items.equals(((RerollItems) other).items));
    }
}
