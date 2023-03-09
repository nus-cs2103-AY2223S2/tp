package seedu.address.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class exists because I cannot find a way of making an unmodifiable/observable standalone value for Users.
 */
public class UserList implements Iterable<User> {

    private final ObservableList<User> internalList = FXCollections.observableArrayList();
    private final ObservableList<User> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Currently not in use. Use SetUser if you want to edit the user.
     */
    public void add(User toAdd) {
        requireNonNull(toAdd);

        internalList.add(toAdd);
    }

    /**
     * Replaces the user object with the new user replacement
     */
    public void setUser(User replacement) {
        requireNonNull(replacement);
        internalList.set(0, replacement);
    }

    /**
     * Replaces the contents of this list with {@code user}.
     */
    public void setUserList(List<User> user) {
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
