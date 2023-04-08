package trackr.model.commons;

import static trackr.commons.util.AppUtil.checkArgument;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline in the list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public abstract class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "%s deadline should only contain numeric values in the format \"DD/MM/YYYY\" and it should not be blank.";

    private static final String VALIDATION_REGEX = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";

    private static final DateTimeFormatter DTF_INPUT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DTF_OUTPUT_DATE = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    private final LocalDate deadline;

    /**
     * Constructs a {@code Deadline}.
     */
    public Deadline(String deadline, String type) {
        requireAllNonNull(deadline, type);
        String error = String.format(MESSAGE_CONSTRAINTS, type);
        checkArgument(isValidDeadline(deadline), error);
        this.deadline = LocalDate.parse(deadline, DTF_INPUT_DATE);
    }

    /**
     * Returns true if a given string is a valid deadline, meaning string is of the format "dd/MM/yyyy".
     */
    public static boolean isValidDeadline(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            LocalDate.parse(test, DTF_INPUT_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Compare this deadline to a given deadline.
     * @return 1 if this deadline is after the given deadline,
     *         -1 if this deadline is before the given status,
     *         0 if both deadlines are the same.
     */
    public int compare(Deadline other) {
        int compareVal = deadline.compareTo(other.deadline);

        if (compareVal == 0) {
            return 0;
        } else {
            return compareVal / Math.abs(compareVal);
        }
    }

    /**
     * Returns the deadline stored in "dd/MM/yyyy" format for json storage.
     * @return A string representation of the deadline.
     */
    public String toJsonString() {
        return deadline.format(DTF_INPUT_DATE);
    }

    /**
     * Returns the deadline stored in "01 JANUARY 2023" format.
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return deadline.format(DTF_OUTPUT_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
