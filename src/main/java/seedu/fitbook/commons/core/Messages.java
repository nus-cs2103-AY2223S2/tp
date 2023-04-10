package seedu.fitbook.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_NO_KEYWORD = "Keyword parameter cannot be empty. \n%1$s";
    public static final String MESSAGE_NO_PREFIX = "Prefix parameter cannot be empty. \n%1$s";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PREFIX = "Invalid prefix inputted! \n%1$s";
    public static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "The client index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d client(s) listed!";
    public static final String MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX = "The routine index provided is invalid";
    public static final String MESSAGE_ROUTINES_LISTED_OVERVIEW = "%1$d routine(s) listed!";
    public static final String MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX = "The exercise index provided is invalid";
    public static final String MESSAGE_INVALID_DATE = "The date and time provided is invalid, date and time should"
            + " be your current date and time or before ";
    public static final String MESSAGE_DUPLICATE_DATE = "There is already a weight for this date and time. Please use"
            + " another date and time.";
}
