package seedu.address.model.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * This class exists because I cannot find a way of making an unmodifiable standalone value for Users. So a list it is.
 */
public class UserList implements Iterable<User> {

    private final ObservableList<User> internalList = FXCollections.observableArrayList();
    private final ObservableList<User> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(User toAdd) {
        requireNonNull(toAdd);

        internalList.add(toAdd);
    }

    public void setUser(UserList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setUser(List<User> user) {
        requireAllNonNull(user);
        internalList.setAll(user);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<User> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<User> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserList // instanceof handles nulls
                && internalList.equals(((UserList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
