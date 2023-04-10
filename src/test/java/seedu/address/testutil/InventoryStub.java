package seedu.address.testutil;

import seedu.address.model.entity.Inventory;

/**
 * Stub class for Inventory
 */
public class InventoryStub extends Inventory {
    @Override
    public boolean equals(Object other) {
        return other instanceof InventoryStub;
    }
}
