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
}

