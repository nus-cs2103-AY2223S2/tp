package seedu.address.model.user;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.event.Event;

/**
 * Wraps all data at the UserData level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class UserData implements ReadOnlyUserData {

    private ReadOnlyObjectWrapper<User> user;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        user = new ReadOnlyObjectWrapper<>(new User());
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
        this.user.setValue(user);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyUserData newData) {
        requireNonNull(newData);

        setUser(newData.getData().getValue());
    }

    //// util methods

    //@Override
    //public String toString() {
    // TODO: refine later
    //}

    @Override
    public ReadOnlyObjectProperty<User> getData() {
        return this.user.getReadOnlyProperty();
    }

    public boolean hasEvent(Event e) {
        return this.user.getValue().hasEvent(e);
    }
    public void addEvent(Event e) {
        this.user.getValue().addEvent(e);
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
}
