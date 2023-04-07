package vimification.model.task;

/**
 * Represents possible status of a task in the application.
 */
public enum Status {

    NOT_DONE, IN_PROGRESS, COMPLETED, OVERDUE;

    /**
     * Returns a status, based on a numeric value.
     */
    public static Status fromInt(int level) {
        switch (level) {
        case 1:
            return IN_PROGRESS;
        case 2:
            return COMPLETED;
        case 3:
            return OVERDUE;
        default:
            return NOT_DONE;
        }
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}

