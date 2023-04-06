package seedu.address.model.tag;

/**
 * Represents the possible types of the auto assigned type to relevant objects in the respective command.
 * TODO for {@code InternshipTodo}, NOTE for {@code Note}, BOTH for {@code InternshipTodo} and {@code Note} in the
 * task-related commands and NONE for the {@code InternshipApplication}.
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
