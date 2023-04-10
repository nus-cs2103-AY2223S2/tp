package seedu.dengue.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!\n"
            + "Valid commands:\n"
            + "add, edit, delete, clear, list, find, sort, undo, redo, "
            + "overview, checkout, import, export, help, exit";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is too big!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INDICATE_POSITIVE = "Indicate a positive number!";

}
