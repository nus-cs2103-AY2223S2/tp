package seedu.address.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import seedu.address.model.user.User;

/**
 * Unmodifiable view of user data.
 */
public interface ReadOnlyUserData {
    /**
     * Returns an unmodifiable view of the user's data.
     * The only item in the array is the user.
     */
    ReadOnlyObjectProperty<User> getUser();

}
