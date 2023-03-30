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

    public static final String MESSAGE_CONSTRAINTS = "%s status should only be %s";
    private final String validationRegex;
    private final HashMap<String, String> statuses = new HashMap<>();

    private final String status;

    /**
     * Constructs a {@code Status}.
     */
    public Status(String status, String type, HashMap<String, String> statuses) {
        requireAllNonNull(status, type, statuses);

        for (Entry<String, String> entry : statuses.entrySet()) {
            this.statuses.put(entry.getKey().toUpperCase(), entry.getValue());
        }

        StringBuilder statusMessageBuilder = new StringBuilder();
        for (Entry<String, String> entry : this.statuses.entrySet()) {
            statusMessageBuilder.append(String.format(" `%s` or `%s` for %s,", entry.getKey(),
                    entry.getKey().toLowerCase(), entry.getValue()));
        }
        String statusMessage =
                statusMessageBuilder.deleteCharAt(statusMessageBuilder.length() - 1).append(".").toString();

        this.validationRegex = String.format("^[%s]$",
                this.statuses.keySet().stream().reduce("", (t, e) -> t + e + e.toLowerCase()));
        checkArgument(isValidStatus(status, statuses), String.format(MESSAGE_CONSTRAINTS, type, statusMessage));
        this.status = status.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test, HashMap<String, String> statuses) {
        String validationRegex = String.format("^[%s]$",
                statuses.keySet().stream().reduce("", (t, e) -> t + e + e.toLowerCase()));
        return test.matches(validationRegex);
    }

    /**
     * Compare this status to a given status.
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
