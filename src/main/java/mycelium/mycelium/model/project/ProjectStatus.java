package mycelium.mycelium.model.project;

/**
 * Represents a project's status.
 */
public enum ProjectStatus {
    NOT_STARTED,
    IN_PROGRESS,
    DONE;

    public static final String MESSAGE_CONSTRAINTS = "Project status should be one of the following: "
            + "not_started, in_progress, done.";

    /**
     * Parses a project's status from its string representation. Throws an
     * {@code IllegalArgumentException} if the input string is not valid. To
     * avoid having to catch this error, consider calling {@code
     * isValidProjectStatus} first.
     */
    public static ProjectStatus fromString(String s) {
        switch (s.toLowerCase()) {
        case "not_started":
            return NOT_STARTED;
        case "in_progress":
            return IN_PROGRESS;
        case "done":
            return DONE;
        default:
            throw new IllegalArgumentException("Invalid project status: " + s);
        }
    }

    @Override
    public String toString() {
        return this == NOT_STARTED ? "not_started" : this == IN_PROGRESS ? "in_progress" : "done";
    }

    /**
     * Checks if a string represents a valid project status.
     */
    public static boolean isValidProjectStatus(String test) {
        test = test.toLowerCase();
        return test.equals("not_started") || test.equals("in_progress") || test.equals("done");
    }
}
