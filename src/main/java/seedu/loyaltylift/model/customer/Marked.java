package seedu.loyaltylift.model.customer;

/**
 * Represents a Customer's bookmarked status in the address book.
 */
public class Marked {
    private final boolean marked;

    /**
     * Constructs a {@code Marked}.
     *
     * @param marked A boolean indicating whether the customer is bookmarked or not.
     */
    public Marked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        return marked ? "Yes" : "No";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Marked // instanceof handles nulls
                && marked == ((Marked) other).marked); // state check
    }
}
