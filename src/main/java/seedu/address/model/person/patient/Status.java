package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Patient's hospitalization status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status should be one of the following: Inpatient, Outpatient, Observation, Emergency Department,"
                    + " Intensive Care Unit, Transitional Care";

    public static final String VALIDATION_REGEX = "(?i)^(Inpatient|Outpatient|Observation|Emergency Department"
            + "|Intensive Care Unit|Transitional Care)$";

    private final String status;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid hospitalization status.
     */
    public Status(String status) {
        requireNonNull(status);
        this.status = toTitleCase(status);
    }

    /**
     * Returns true if a given string is a valid hospitalization status.
     *
     * @param test the input string to test the validation regex against
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a valid Status string.
     *
     * @return a valid Status string.
     */
    public static String getDummyValidStatus() {
        return "Inpatient";
    }

    /**
     * Converts the input string to title case.
     *
     * @param input the input string
     */
    private static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
    @Override
    public String toString() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && status.equalsIgnoreCase(((Status) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.toLowerCase().hashCode();
    }
}
