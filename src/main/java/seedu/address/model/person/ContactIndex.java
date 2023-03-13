package seedu.address.model.person;

import java.util.Objects;

import seedu.address.model.person.exceptions.InvalidContactIndexException;

/**
 * Represents the index of the person in EduMate
 */
public class ContactIndex {

    private final int contactIndex;

    /**
     * Creates a new index for the specific contact.
     * @param zeroBasedIndex index of the contact.
     */
    public ContactIndex(int zeroBasedIndex) {
        if (zeroBasedIndex < 0) {
            throw new InvalidContactIndexException();
        }
        this.contactIndex = zeroBasedIndex;
    }

    public int getContactIndex() {
        return contactIndex;
    }

    public int getValue() {
        return contactIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactIndex that = (ContactIndex) o;
        return contactIndex == that.getContactIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactIndex);
    }

    @Override
    public String toString() {
        return String.valueOf(contactIndex);
    }
}
