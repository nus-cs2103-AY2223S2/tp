package seedu.library.model.bookmark;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents a Bookmark's genre in the library.
 * Guarantees: immutable; is valid as declared in {@link #isValidGenre(String)}
 */
public class Genre {

    public static final String MESSAGE_CONSTRAINTS = "Genre can take any values, and it should not be blank";

    /*
     * The first character of the genre must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Genre}.
     *
     * @param genre A valid genre.
     */
    public Genre(String genre) {
        requireNonNull(genre);
        checkArgument(isValidGenre(genre), MESSAGE_CONSTRAINTS);
        value = genre;
    }

    /**
     * Returns true if a given string is a valid genre.
     */
    public static boolean isValidGenre(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Genre // instanceof handles nulls
                && value.equals(((Genre) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
