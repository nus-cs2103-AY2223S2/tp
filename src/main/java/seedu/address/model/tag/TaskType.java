package seedu.address.model.tag;

/**
 * Represents the possible types for an auto assigned type of task and note.
 */
public enum TaskType {
    TODO,
    NOTE,
    BOTH,
    NONE;

    public static final String MESSAGE_CONSTRAINTS = "%s type is invalid!";

    /**
     * Checks if the type in string is TODO.
     */
    public static boolean isValidTodo(String type) {
        return type.equals((TaskType.TODO).toString());
    }

    /**
     * Checks if the type in string is NOTE.
     */
    public static boolean isValidNote(String type) {
        return type.equals((TaskType.NOTE).toString());
    }
}
