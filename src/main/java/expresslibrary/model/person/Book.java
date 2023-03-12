package expresslibrary.model.person;
import static expresslibrary.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a book object.
 */
public class Book {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public final String title;

    /**
     * Constructor for book object.
     * @param title Title of book
     */
    public Book(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    public static boolean isValidTitle(String title) {
        return title.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return title;
    }
}
