package trackr.model.menu;

import trackr.model.commons.Name;

/**
 * Represents a Item's name in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
//@@author changgittyhub-reused
public class ItemName extends Name {

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    public static final String MESSAGE_CONSTRAINTS =
            "Item names should only contain alphanumeric characters and spaces, and it should not be blank";
    //@@author

    /**
     * Constructs an {@code ItemName}.
     *
     * @param itemName A valid Item name.
     */
    public ItemName(String itemName) {
        super(itemName, "Item");
    }
}
