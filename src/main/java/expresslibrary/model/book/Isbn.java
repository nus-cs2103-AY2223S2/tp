package expresslibrary.model.book;

import static expresslibrary.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;
/**
 * Represents a Book's ISBN in the express library.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsbn(String)}
 */
public class Isbn {
    public static final String VALIDATION_REGEX = "^\\d{10}(\\d{3})?$";
    public static final String MESSAGE_CONSTRAINTS = "ISBNs should consist of 10 or 13 digits. The number is divided"
            + " into four parts of variable length, each part separated by a hyphen.";
    public final String isbn;

    /**
     * Constructor for ISBN object.
     *
     * @param isbn The ISBN of the book
     */
    public Isbn(String isbn) {
        requireNonNull(isbn);
        checkArgument(isValidIsbn(isbn), MESSAGE_CONSTRAINTS);
        this.isbn = isbn;
    }

    public static boolean isValidIsbn(String isbn) {
        return isbn.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return isbn;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Isbn // instanceof handles nulls
                        && isbn.equals(((Isbn) other).isbn)); // state check
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
