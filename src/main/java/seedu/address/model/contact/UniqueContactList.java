package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.exceptions.ContactNotFoundException;
import seedu.address.model.contact.exceptions.DuplicateContactException;


public class UniqueContactList implements Iterable<Contact> {

    private final ObservableList<Contact> internalList = FXCollections.observableArrayList();
    private final ObservableList<Contact> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Contact as the given argument.
     */
    public boolean contains(Contact toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameContact);
    }

    /**
     * Adds a Contact to the list.
     * The Contact must not already exist in the list.
     */
    public void add(Contact toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateContactException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the list.
     * The Contact identity of {@code editedContact} must not be the same as another existing Contact in the list.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ContactNotFoundException();
        }

        if (!target.isSameContact(editedContact) && contains(editedContact)) {
            throw new DuplicateContactException();
        }

        internalList.set(index, editedContact);
    }

    /**
     * Removes the equivalent Contact from the list.
     * The Contact must exist in the list.
     */
    public void remove(Contact toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ContactNotFoundException();
        }
    }

    public void setContacts(seedu.address.model.contact.UniqueContactList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Contacts}.
     * {@code Contacts} must not contain duplicate Contacts.
     */
    public void setContacts(List<Contact> Contacts) {
        requireAllNonNull(Contacts);
        if (!ContactsAreUnique(Contacts)) {
            throw new DuplicateContactException();
        }

        internalList.setAll(Contacts);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Contact> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Contact> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.contact.UniqueContactList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.contact.UniqueContactList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Contacts} contains only unique Contacts.
     */
    private boolean ContactsAreUnique(List<Contact> Contacts) {
        for (int i = 0; i < Contacts.size() - 1; i++) {
            for (int j = i + 1; j < Contacts.size(); j++) {
                if (Contacts.get(i).isSameContact(Contacts.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
