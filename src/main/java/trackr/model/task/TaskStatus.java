package trackr.model.task;

import java.util.HashMap;

import trackr.model.commons.Status;

/**
 * Represents a Task's status in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String, HashMap)}
 */
public class TaskStatus extends Status {

    public static final HashMap<String, String> STATUSES;

    static {
        STATUSES = new HashMap<>();
        STATUSES.put("N", "Not Done");
        STATUSES.put("D", "Done");
    }

    /**
     * Constructs a {@code TaskStatus}.
     *
     * @param status A valid status.
     */
    public TaskStatus(String status) {
        super(status, "Task", STATUSES);
    }

    /**
     * Constructs a {@code TaskStatus}.
     */
    public TaskStatus() {
        this("N");
    }

    /**
     * Compare this status to a given status.
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

