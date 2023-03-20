package expresslibrary.model.book;

import static expresslibrary.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Book's Author in the express library.
 * Guarantees: immutable; is valid as declared in {@link #isValidAuthor(String)}
 */
public class Author {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters and spaces,"
            + " and it should not be blank";
    public final String name;

    /**
     * Constructor for author object.
     *
     * @param name Name of author.
     */
    public Author(String name) {
        requireNonNull(name);
        checkArgument(isValidAuthor(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    public static boolean isValidAuthor(String name) {
        return name.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Isbn // instanceof handles nulls
                        && name.equals(((Isbn) other).isbn)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
