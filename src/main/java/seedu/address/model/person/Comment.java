package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Comment {

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */

    public final String value;

    public Comment(String comment) {
        requireNonNull(comment);
        value = comment;
    }
    public Comment() {
        value = "No comment";
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Comment // instanceof handles nulls
                && value.equals(((Comment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
