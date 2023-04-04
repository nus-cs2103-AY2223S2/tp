package mycelium.mycelium.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_CHECK_USER_GUIDE = "Please visit our User Guide for more information.";
    public static final String MESSAGE_INVALID_CLIENT = "The client email entered does not match any client";
    public static final String MESSAGE_INVALID_PROJECT = "The project name entered does not match any project";
    public static final String MESSAGE_INVALID_DATE = "The date entered is invalid";

    public static final String MESSAGE_EMPTY_PROJECT_NAME =
        "Project name cannot be empty or consist of only whitespace";
    public static final String
        MESSAGE_ILLEGAL_PROJECT_NAME_FMT =
        "'%s' is an illegal project name as it interferes with parsing. Please consider differentiating it from an "
            + "argument flag.";
    public static final String MESSAGE_EMPTY_SOURCE = "Source cannot be empty or consist of only whitespace";
}
