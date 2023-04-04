package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents whether or not the contact is marked.
 * Guarantees: is valid as declared in {@link #isValidMark(String)}
 */
public class Mark {

    public static final String MESSAGE_CONSTRAINTS =
            "Mark should only be 'yes' or 'no', and it should not be blank.";

    public final boolean isMark;

    /**
     * Constructs an {@code BusinessSize}.
     *
     * @param mark Whether the contact is marked.
     */
    public Mark(String mark) {
        requireNonNull(mark);
        checkArgument(isValidMark(mark), MESSAGE_CONSTRAINTS);
        this.isMark = mark.toLowerCase(Locale.ROOT).equals("yes");
    }

    /**
     * @param mark The mark to be checked.
     */
    public static boolean isValidMark(String mark) {
        return mark.trim().toLowerCase(Locale.ROOT).equals("yes")
                || mark.trim().toLowerCase(Locale.ROOT).equals("no");
    }

    @Override
    public String toString() {
        return isMark ? "YES" : "NO";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mark // instanceof handles nulls
                && isMark == ((Mark) other).isMark); // state check
    }

}
