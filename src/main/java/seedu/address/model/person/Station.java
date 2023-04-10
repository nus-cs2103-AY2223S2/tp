package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.location.Location;
import seedu.address.model.location.util.LocationDataUtil;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStation(String)}
 */
public class Station implements Comparable<Station> {

    public static final String MESSAGE_CONSTRAINTS =
            "Stations should be the "
            + "names of the closest MRT stations to their homes.";

    private final Location value;

    /**
     * Constructs an {@code Station}.
     *
     * @param station A valid station.
     */
    public Station(String station) {
        requireNonNull(station);
        checkArgument(isValidStation(station), MESSAGE_CONSTRAINTS);
        value = LocationDataUtil.STATIONS_HASH_MAP.get(station.toLowerCase());
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidStation(String test) {
        return test != null
                && LocationDataUtil.STATIONS_HASH_MAP.containsKey(test.trim().toLowerCase());
    }

    /**
     * Gets the String value stored within the email.
     */
    public Location getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue().getName();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Station // instanceof handles nulls
                && value.equals(((Station) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Station otherStation) {
        return value.compareTo(otherStation.value);
    }

}
