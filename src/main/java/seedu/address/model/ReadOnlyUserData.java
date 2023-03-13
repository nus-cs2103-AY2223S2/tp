package seedu.address.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import seedu.address.model.user.User;
import seedu.address.storage.ReadOnlyData;

/**
 * Unmodifiable view of user data.
 */
public interface ReadOnlyUserData extends ReadOnlyData {
    /**
     * Returns an unmodifiable view of the user's data.
     * The only item in the array is the user.
     */
    @Override
    ReadOnlyObjectProperty<User> getData();

}
