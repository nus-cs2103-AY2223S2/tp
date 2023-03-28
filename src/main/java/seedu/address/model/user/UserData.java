package seedu.address.model.user;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Wraps all data at the UserData level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class UserData implements ReadOnlyUserData {

    private final ReadOnlyObjectWrapper<User> user;

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

    //// Event methods

    public void addEvent(Event e) {
        this.user.getValue().addEvent(e);
    }

    public void deleteEvent(Event event) {
        this.user.getValue().deleteEvent(event);
    }

    public boolean hasEvent(Event e) {
        return this.user.getValue().hasEvent(e);
    }

    public void deletePersonFromAllEvents(Person target) {
        this.user.getValue().deletePersonFromAllEvents(target);
    }

    public void tagPersonToEvent(Index index, Person p) {
        this.user.getValue().tagPersonToEvent(index, p);
    }

    public void untagPersonFromEvent(Index index, Person p) {
        this.user.getValue().untagPersonFromEvent(index, p);
    }

    public boolean isPersonTaggedToEvent(Index index, Person p) {
        return this.user.getValue().isPersonTaggedToEvent(index, p);
    }


    //// util methods

    public void setEvent(Event target, Event editedEvent) {
        this.user.getValue().setEvent(target, editedEvent);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserData // instanceof handles nulls
                && user.equals(((UserData) other).user));
    }

    @Override
    public String toString() {
        return this.user.toString();
    }

    @Override
    public ReadOnlyObjectProperty<User> getData() {
        return this.user.getReadOnlyProperty();
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}
