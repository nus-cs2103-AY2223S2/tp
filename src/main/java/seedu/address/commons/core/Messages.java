package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";

    public static final String MESSAGE_HOMEWORK_ADDED_SUCCESS = "New homework added:\n%s\n"
            + "To the following students:\n%s";

    public static final String MESSAGE_HOMEWORK_LISTED_OVERVIEW = "%d homework from %d student listed:\n%s";
    public static final String MESSAGE_ALL_HOMEWORK_LISTED_OVERVIEW = "%d homework from all student listed:\n%s";
    public static final String MESSAGE_NO_HOMEWORK_FOUND = "No homework found";
    public static final String MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX = "The homework index provided is invalid";
    public static final String MESSAGE_HOMEWORK_DELETED_SUCCESS = "Homework : %s. %s\n"
            + "Deleted from the %s\n";
    public static final String MESSAGE_HOMEWORK_ALREADY_MARKED_AS_DONE =
            "Homework %s of student %s is already marked as done\n";
    public static final String MESSAGE_HOMEWORK_MARKED_AS_DONE = "Homework %s of student %s is marked as done\n";
    public static final String MESSAGE_HOMEWORK_MARKED_AS_UNDONE = "Homework %s of student %s is marked as undone\n";
    public static final String MESSAGE_HOMEWORK_ALREADY_MARKED_AS_UNDONE =
            "Homework %s of student %s is already marked as undone\n";
    public static final String MESSAGE_INVALID_STUDENT_NAME = "No student found!\n";
}
