package arb.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "The client index provided is invalid";
    public static final String MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX = "The project index provided is invalid";
    public static final String MESSAGE_CLIENTS_LISTED_OVERVIEW = "%1$d clients listed!";
    public static final String MESSAGE_INVALID_LIST_PROJECT = "The project list is currently not being shown! "
            + "You can switch to the project list with the command 'list-project'";

    public static final String MESSAGE_INVALID_LIST_CLIENT = "The client list is currently not being shown! "
            + "You can switch to the client list with the command 'list-client'";

    public static final String MESSAGE_DUPLICATE_CLIENT = "The edited client at the index provided is the same as" +
            " an existing client!";

    public static final String MESSAGE_DUPLICATE_PROJECT = "The edited project at the index provided is the same as" +
            " an existing project!";

}
