package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Represents a Meeting's location in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {
    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the location must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String meetingLocation;
    private final boolean isVirtualLocation;

    /**
     * Constructs a {@code Location}.
     *
     * @param location A valid description.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        meetingLocation = location;
        isVirtualLocation = isValidUrl(location);
    }

    public boolean isVirtualLocation() {
        return isVirtualLocation;
    }

    /**
     * Returns true if a given string is a valid description.
     *
     * @param test A string to test for validity.
     * @return True if string is valid, false otherwise.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid URL.
     *
     * @param url A string to test if it is a URL.
     * @return True if string is a valid URL, false otherwise.
     */
    private boolean isValidUrl(String url) {
        try {
            new URI(url);
        } catch (URISyntaxException e) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return meetingLocation;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Location // instanceof handles nulls
                && meetingLocation.equals(((Location) other).meetingLocation)); // state check
    }

    @Override
    public int hashCode() {
        return meetingLocation.hashCode();
    }
}
