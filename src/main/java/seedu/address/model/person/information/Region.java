package seedu.address.model.person.information;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;

/**
 * Represents a Person's region in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidRegion(String)}
 */
public class Region {
    public static final String MESSAGE_CONSTRAINTS =
            "Region should only contain 5 types of values, "
            + "north, northeast, east, west, central";
    public final Place region;

    /**
     * The enum for Region: north, northeast, east, west, central
     */
    public enum Place {
        NORTH,
        NORTHEAST,
        EAST,
        WEST,
        CENTRAL
    }

    /**
     * Constructs an {@code Region}.
     *
     * @param region A valid region.
     */
    public Region(String region) {
        requireNonNull(region);
        this.region = Place.valueOf(region);
    }

    /**
     * Returns true if a given string is a valid region.
     */
    public static boolean isValidRegion(String region) {
        HashSet<String> set = new HashSet<>();
        for (Place regionSet: Place.values()) {
            set.add(regionSet.name());
        }
        return set.contains(region.toUpperCase());
    }

    @Override
    public String toString() {
        return region.toString().toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Region
                && region.equals(((Region) other).region));
    }

    @Override
    public int hashCode() {
        return region.hashCode();
    }

}
