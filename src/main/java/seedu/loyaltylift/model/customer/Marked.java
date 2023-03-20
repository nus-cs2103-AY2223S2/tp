package seedu.loyaltylift.model.customer;

/**
 * Represents a Customer's bookmarked status in the address book.
 */
public class Marked {
    public final boolean value;

    /**
     * Constructs a {@code Marked}.
     *
     * @param marked A boolean indicating whether the customer is bookmarked or not.
     */
    public Marked(boolean marked) {
        this.value = marked;
    }

    @Override
    public String toString() {
        return value ? "Yes" : "No";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Marked // instanceof handles nulls
                && value == ((Marked) other).value); // state check
    }
}
