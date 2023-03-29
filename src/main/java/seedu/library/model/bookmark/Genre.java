package seedu.library.model.bookmark;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Bookmark's genre in the library.
 * Guarantees: immutable; is valid as declared in {@link #isValidGenre(String)}
 */
public class Genre {

    public static final String MESSAGE_CONSTRAINTS = "Genre can take on values in the pre-defined list, "
            + "and it should not be blank\n"
            + "View list of valid genres with the command: genre";

    public static final List<String> VALID_GENRES = Arrays.asList("Action", "Adventure", "Comedy", "Drama",
            "Fantasy", "Historical", "Horror", "Martial Arts", "Sci-Fi", "Mystery", "Romance", "Sports", "Others");

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
     * Constructs a regex string to match valid genres.
     * @return Regex string.
     */
    private static String getValidationRegex() {
        String validGenreOptions = String.join("|", VALID_GENRES);
        return "^(" + validGenreOptions + ")$";
    }

    public static List<String> getValidGenres() {
        return VALID_GENRES;
    }

    /**
     * Returns true if a given string is a valid genre.
     */
    public static boolean isValidGenre(String test) {
        String validationRegex = getValidationRegex();
        return test.matches(validationRegex);
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
