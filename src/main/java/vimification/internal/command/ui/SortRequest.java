package vimification.internal.command.ui;

/**
 * Represents a structure that stores relevant information for the execution of {@link SortCommand}.
 */
public class SortRequest {

    /**
     * Different fields to sort by. Only 1 field can be sorted at a time.
     */
    public static enum Mode {
        /**
         * Sorts by deadline. Null comes last.
         */
        DEADLINE,

        /**
         * Sorts by priority.
         */
        PRIORITY,

        /**
         * Sorts by status.
         */
        STATUS
    }

    private Mode mode;

    /**
     * Create a new {@code SortRequest} instance with default values.
     */
    public SortRequest() {}

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
