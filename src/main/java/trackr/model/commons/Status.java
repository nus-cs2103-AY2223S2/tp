package trackr.model.commons;

import static trackr.commons.util.AppUtil.checkArgument;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Represents a status in the list.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String, HashMap)}
 */
public abstract class Status {

    public static final String MESSAGE_CONSTRAINTS_FORMAT = "%s status should only be %s";
    private final HashMap<String, String> statuses = new HashMap<>();

    private final String status;

    /**
     * Constructs a {@code Status}.
     *
     * @param status The status string given by user.
     * @param type The type of status. (e.g. order stauts, task status, etc.)
     * @param statuses The HashMap of valid statues to be created
     *                 and used to check the given status for validity.
     */
    public Status(String status, String type, HashMap<String, String> statuses) {
        requireAllNonNull(status, type, statuses);

        for (Entry<String, String> entry : statuses.entrySet()) {
            this.statuses.put(entry.getKey().toUpperCase(), entry.getValue());
        }

        checkArgument(isValidStatus(status, statuses),
                String.format(MESSAGE_CONSTRAINTS_FORMAT, type, getStatusMessage()));
        this.status = status.toUpperCase();
    }

    /**
     * Checks if a given status String conforms to the expected format.
     *
     * @param test The string to check.
     * @param statuses The HashMap of valid string to check the given string with.
     * @return Returns true if a given string is of valid format, false otherwise.
     */
    public static boolean isValidStatus(String test, HashMap<String, String> statuses) {
        String validationRegex = String.format("^[%s]$",
                statuses.keySet().stream().reduce("", (t, e) -> t + e + e.toLowerCase()));
        return test.matches(validationRegex);
    }

    /**
     * Returns a string that represents the types of valid statuses.
     *
     * @return A String Message that list out the valid statues.
     */
    public String getStatusMessage() {
        StringBuilder statusMessageBuilder = new StringBuilder();
        for (Entry<String, String> entry : this.statuses.entrySet()) {
            statusMessageBuilder.append(String.format(" `%s` or `%s` for %s,", entry.getKey(),
                    entry.getKey().toLowerCase(), entry.getValue()));
        }
        String statusMessage =
                statusMessageBuilder.deleteCharAt(statusMessageBuilder.length() - 1).append(".").toString();

        return statusMessage;
    }

    /**
     * Compares this status to a given status.
     *
     * @other The status to compare this status with.
     * @return -1, 1 or 0 according to the sorting criteria.
     */
    public abstract int compare(Status other);

    public String toJsonString() {
        return status;
    }

    @Override
    public String toString() {
        return statuses.get(status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && status.equals(((Status) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

}
