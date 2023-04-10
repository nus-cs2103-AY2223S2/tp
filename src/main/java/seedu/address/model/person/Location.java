package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Represents a Person's value in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS =
            "Location should be a valid country that contains characters and spaces, and it should not be blank";

    /*
     * The first character of the value must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private static Set<String> countries = new HashSet<String>();

    public final String value;

    /**
     * Constructs a {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        this.value = location;
    }

    private static void getCountries() {
        if (countries.size() == 0) {
            for (String iso : Locale.getISOCountries()) {
                Locale locale = new Locale("", iso);
                countries.add(locale.getDisplayCountry());
            }
        }
    }

    private static boolean locationExists(String location) {
        getCountries();

        if (countries.contains(location)) {
            return true;
        }

        for (String country : countries) {
            if (country.contains(location)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if a given string is a valid value.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX) && locationExists(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Location // instanceof handles nulls
                && value.equals(((Location) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
