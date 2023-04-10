package seedu.connectus.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_HELP = "Use help to see available commands!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_HELP = "Command does not exist!"
            + "\nRefer to the User Guide for a list of supported commands.\n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_DISPLAYED_INDEX = "The %s index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d person(s) listed!";
    public static final String MESSAGE_PERSONS_UPCOMING_BIRTHDAY = "%1$d person(s) will have upcoming"
            + " birthdays in the next 60 days!";
    public static final String MESSAGE_PERSON_FIELD_NOT_PRESENT = "The person has no such field";
    public static final String MESSAGE_PERSON_TOO_MANY_MAJORS = "The total number of majors cannot be "
            + "greater than 2!";
}
