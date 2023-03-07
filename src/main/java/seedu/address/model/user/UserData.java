package seedu.address.model.user;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyUserData;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class UserData implements ReadOnlyUserData {

    private UserList user;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        user = new UserList();
    }

    public UserData() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public UserData(ReadOnlyUserData toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the user with {@code user}.
     */
    public void setUser(User user) {
        this.user.setUser(user);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyUserData newData) {
        requireNonNull(newData);

        setUser(newData.getUser().get(0));
    }

    //// util methods

    //@Override
    //public String toString() {
    // TODO: refine later
    //}

    @Override
    public ObservableList<User> getUser() {
        return this.user.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserData // instanceof handles nulls
                && user.equals(((UserData) other).user));
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    public void addUser(User user) {
        this.user.add(user);
    }

    public void setUserList(List<User> user) {
        this.user = new UserList();
    }
}
