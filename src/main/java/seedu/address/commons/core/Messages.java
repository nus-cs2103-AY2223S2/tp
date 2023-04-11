package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command. Try 'student' or 'parent' "
            + "or 'list' or 'help'";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX =
            "The student index provided is invalid/ not found";
    public static final String MESSAGE_INVALID_STUDENT_CLASS = "The student class provided is invalid/not found";
    public static final String MESSAGE_STUDENT_NOT_FOUND =
            "The student with the corresponding index number and class cannot be not found";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_HOMEWORK = "This homework already exists!";
    public static final String MESSAGE_DUPLICATE_TEST = "This test already exists in the address book";
    public static final String MESSAGE_INVALID_PARENT_DELETE = "The parent you are trying to delete has a "
            + "student attached! You can't delete the parent!";
    public static final String MESSAGE_PARENT_NOT_FOUND = "The parent name and parent number provided does not match "
            + "any of the parents in our parent list!";
    public static final String MESSAGE_DUPLICATE_ATTENDANCE = "Attendance already marked!";
    public static final String MESSAGE_BLANK_FIND = "Please enter letters to find a name!";

    public static final String MESSAGE_INVALID_WEIGHTAGE =
            "Error! The weightage should add up to less than or equals to 100%";
}
