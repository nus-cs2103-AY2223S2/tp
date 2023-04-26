package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Represents a Patient's hospitalization status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status should be one of the following: Inpatient, Outpatient, Observation, Emergency Department,"
                    + " Intensive Care Unit, Transitional Care";

    private static final String VALIDATION_REGEX = "(?i)^(Inpatient|Outpatient|Observation|Emergency Department"
            + "|Intensive Care Unit|Transitional Care)$";

    private static final Logger logger = LogsCenter.getLogger(Status.class);

    private static final ArrayList<String> possibleStatuses = new ArrayList<>(
            List.of("Inpatient", "Outpatient", "Observation", "Emergency Department",
                    "Intensive Care Unit", "Transitional Care"));

    private final String status;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid hospitalization status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
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
        return getDummyValidStatus(0);
    }

    /**
     * Returns a valid Status string corresponding with the given index.
     *
     * @param i an index specifying the status to retrieve.
     * @return a valid Status string.
     */
    public static String getDummyValidStatus(int i) {
        if (i >= possibleStatuses.size()) {
            logger.warning(String.format("Received an invalid index of %d,"
                                    + "when number of statuses available is %d",
                    i, possibleStatuses.size()));
            logger.info("Retrieving default valid status instead");
            return getDummyValidStatus();
        }
        return possibleStatuses.get(i);
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

    public String getValue() {
        return status;
    }
}
