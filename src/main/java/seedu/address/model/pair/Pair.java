package seedu.address.model.pair;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;

/**
 * Represents a Pair of elderly and volunteer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Pair {

    // Identity fields
    private final Elderly elderly;

    private final Volunteer volunteer;

    /**
     * Every field must be present and not null.
     */
    public Pair(Elderly elderly, Volunteer volunteer) {
        requireAllNonNull(elderly, volunteer);
        this.elderly = elderly;
        this.volunteer = volunteer;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    /**
     * Returns true if both pairs have the same volunteer and elderly.
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
