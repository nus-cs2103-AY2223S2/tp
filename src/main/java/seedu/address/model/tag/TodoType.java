package seedu.address.model.tag;

/**
 * Represents the possible statuses for an auto assigned type of data.
 */
public enum TodoType {
    TODO,
    NOTE,
    BOTH,
    NONE;

    public static final String MESSAGE_CONSTRAINTS = "%s type is invalid!";

    public static boolean isValidTodo(String type) {
        return type.equals((TodoType.TODO).toString());
    }

    public static boolean isValidNote(String type) {
        return type.equals((TodoType.NOTE).toString());
    }
}
