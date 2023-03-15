package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
abstract public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE_SINGLE_DELETE = "1. Delete Single Index"
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE_MULTIPLE_DELETE = "2. Delete Multiple Indexes"
            + ": Deletes multiple people identified by the index numbers used in the displayed person list.\n"
            + "Parameters: INDEXES (must be a positive integer), with each index being separated by a comma \n"
            + "Example: " + COMMAND_WORD + " 1,2,3";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "\n"
            + MESSAGE_USAGE_SINGLE_DELETE
            + "\nOR\n"
            + MESSAGE_USAGE_MULTIPLE_DELETE;
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

}
