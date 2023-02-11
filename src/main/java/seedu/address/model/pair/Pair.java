package seedu.address.model.pair;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a Pair of elderly and volunteer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Pair {

    // Identity fields
    private final Person elderly;

    private final Person volunteer;

    /**
     * Every field must be present and not null.
     */
    public Pair(Person elderly, Person volunteer) {
        requireAllNonNull(elderly, volunteer);
        this.elderly = elderly;
        this.volunteer = volunteer;
    }

    public Person getElderly() {
        return elderly;
    }

    public Person getVolunteer() {
        return volunteer;
    }

    /**
     * Returns true if both pairs have the same volunteer name and elderly name.
     * This defines a weaker notion of equality between two pairs.
     */
    public boolean isSamePair(Pair otherPair) {
        if (otherPair == this) {
            return true;
        }

        return otherPair != null
                && getElderly().isSamePerson(otherPair.getElderly())
                && getVolunteer().isSamePerson(otherPair.getVolunteer());
    }

    /**
     * Returns true if both pairs have the same elderly and volunteer.
     * This defines a stronger notion of equality between two pairs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pair)) {
            return false;
        }

        Pair otherPair = (Pair) other;
        return getElderly().equals(otherPair.getElderly())
                && getVolunteer().equals(otherPair.getVolunteer());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(elderly, volunteer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Elderly: ")
                .append(getElderly())
                .append("; Volunteer: ")
                .append(getVolunteer());

        return builder.toString();
    }

}
