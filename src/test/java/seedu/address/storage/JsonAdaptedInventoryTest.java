package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalFields.BIG_INVENTORY;

import org.junit.jupiter.api.Test;

import seedu.address.model.entity.Inventory;

public class JsonAdaptedInventoryTest {

    @Test
    public void toModelType_validInventoryDetails_success() throws Exception {
        Inventory validInventory = BIG_INVENTORY;
        JsonAdaptedInventory inventory = new JsonAdaptedInventory(validInventory);
        assertEquals(validInventory, inventory.toModelType());
    }

}
