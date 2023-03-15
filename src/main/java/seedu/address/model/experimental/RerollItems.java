package seedu.address.model.experimental;

import seedu.address.model.entity.Item;

/** Abstraction of all operations on items */
public class RerollItems extends RerollEntities<Item> {

    // For convenience
    private final UniqueEntityList items = entities;

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollItems
                && items.equals(((RerollItems) other).items));
    }
}
