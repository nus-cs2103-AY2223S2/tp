package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Tests that a {@code Opening}'s {@code Company} or {@code Position} matches any of the keywords given.
 */
public class KeydateSort implements Predicate<Opening> {
    public static final String MESSAGE_CONSTRAINTS =
            "ORDER should only either be asc or desc";

    private final String direction;

    public KeydateSort(String direction) {
        requireNonNull(direction);
        checkArgument(isValidOrder(direction), MESSAGE_CONSTRAINTS);
        this.direction = direction.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidOrder(String direction) {
        if (direction.toUpperCase().equals("ASC") || direction.toUpperCase().equals("DESC")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean test(Opening opening) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }

    public String getDirection() {
        return direction;
    }

}
