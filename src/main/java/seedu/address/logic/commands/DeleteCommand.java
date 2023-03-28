package seedu.address.logic.commands;


/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE_SINGLE_DELETE = "1. Delete Single Index"
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE_MULTIPLE_DELETE = "2. Delete Multiple Indexes"
            + ": Deletes multiple people identified by the index numbers used in the displayed person list.\n"
            + "Parameters: INDEXES (must be a positive integer), with each index being separated by a comma \n"
            + "Example: " + COMMAND_WORD + " 1,2,3";

    public static final String MESSAGE_USAGE_DELETE_BY_NAME = "3. Delete Single Person By Name"
            + ": Deletes the person identified by the specified keyword (case-insensitive) used.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "\n"
            + MESSAGE_USAGE_SINGLE_DELETE
            + "\nOR\n"
            + MESSAGE_USAGE_MULTIPLE_DELETE
            + "\nOR\n"
            + MESSAGE_USAGE_DELETE_BY_NAME;
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

}
