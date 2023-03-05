package seedu.address.model.util;

import javafx.collections.ObservableList;
import seedu.address.model.User;

public interface ReadOnlyUserData {
    /**
     * Returns an unmodifiable view of the user's data.
     * The only item in the array is the user.
     */
    ObservableList<User> getUser();
}
