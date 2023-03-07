package seedu.address.model;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;

public interface ReadOnlyUserData {
    /**
     * Returns an unmodifiable view of the user's data.
     * The only item in the array is the user.
     */
    ObservableList<User> getUser();

}
