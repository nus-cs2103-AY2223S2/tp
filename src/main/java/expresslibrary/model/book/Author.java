package expresslibrary.model.book;

import static expresslibrary.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Book's Author in the express library.
 * Guarantees: immutable; is valid as declared in {@link #isValidAuthor(String)}
 */
public class Author {

    public static final String VALIDATION_REGEX = "^(?=.*[a-zA-Z0-9])[a-zA-Z0-9\\s-:;.!()_'`/?]+$";

    public static final String MESSAGE_CONSTRAINTS = "Authors' names should only contain alphanumeric characters, "
            + "spaces and certain special characters, and it should not be blank.";

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
                || (other instanceof Author // instanceof handles nulls
                        && name.equals(((Author) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
