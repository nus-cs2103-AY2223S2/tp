package trackr.model.task;

import java.util.HashMap;
import java.util.Map;

import trackr.model.commons.Status;

/**
 * Represents a Task's status in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String, HashMap)}
 */
public class TaskStatus extends Status {

    public static final HashMap<String, String> STATUSES;
    public static final String STATUS_MESSAGE;
    public static final String MESSAGE_CONSTRAINTS;

    static {
        STATUSES = new HashMap<>();
        STATUSES.put("N", "Not Done");
        STATUSES.put("D", "Done");

        StringBuilder statusMessageBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : STATUSES.entrySet()) {
            statusMessageBuilder.append(String.format(" `%s` or `%s` for %s,", entry.getKey(),
                    entry.getKey().toLowerCase(), entry.getValue()));
        }
        STATUS_MESSAGE =
                statusMessageBuilder.deleteCharAt(statusMessageBuilder.length() - 1).append(".").toString();
        MESSAGE_CONSTRAINTS = String.format(Status.MESSAGE_CONSTRAINTS_FORMAT, "Task", STATUS_MESSAGE);
    }

    /**
     * Constructs a {@code TaskStatus} with a given status input.
     *
     * @param status A valid status.
     */
    public TaskStatus(String status) {
        super(status, "Task", STATUSES);
    }

    /**
     * Constructs a {@code TaskStatus} with no status input given (default case).
     */
    public TaskStatus() {
        this("N");
    }

    /**
     * Compare this status to a given status.
     *
     * @param other The other task status to compare this status with.
     * @return 1 if this status is done and the given status is not done,
     *         -1 if this status is not done and the given status is done
     *         0 if both statuses are the same.
     */

    @Override
    public int compare(Status other) {
        //this status is done and given status is not done
        if (toJsonString().equalsIgnoreCase("D")
                && other.toJsonString().equalsIgnoreCase("N")) {
            return 1;
        }
        //this status is not done and given status is done
        if (toJsonString().equalsIgnoreCase("N")
                && other.toJsonString().equalsIgnoreCase("D")) {
            return -1;
        }
        //both status are the same
        return 0;
    }

}

