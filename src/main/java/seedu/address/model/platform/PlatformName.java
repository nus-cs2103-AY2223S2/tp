package seedu.address.model.platform;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Platform's name in a Listing.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class PlatformName {
    public static final String MESSAGE_CONSTRAINTS =
            "Platform Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullPlatformName;

    /**
     * Constructs a {@code Name}.
     *
     * @param platformName A valid name.
     */
    public PlatformName(String platformName) {
        requireNonNull(platformName);
        checkArgument(isValidName(platformName), MESSAGE_CONSTRAINTS);
        String trimmedName = platformName.trim();
        fullPlatformName = trimmedName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullPlatformName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.platform.PlatformName // instanceof handles nulls
                && fullPlatformName
                .equals(((seedu.address.model.platform.PlatformName) other).fullPlatformName)); // state check
    }

}
