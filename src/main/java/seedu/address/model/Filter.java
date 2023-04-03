package seedu.address.model;

/**
 * This class encapsulates a filtering that user inputted and succesfully executed.
 */
public class Filter {
    private final String filter;

    public Filter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return this.filter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Filter // instanceof handles nulls
                && filter.equals(((Filter) other).filter)); // state check
    }
}
