package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVAID_PART_REQUESTED;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.PartMap;


/**
 * Finds and returns the part details of the provided id.
 */
public class ViewPartCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "viewpart";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the part identified by the part name.\n"
            + "Parameters: STRING\n"
            + "Example: " + COMMAND_WORD + " CS-2103-T";

    private final String userString;

    public ViewPartCommand(String userString) {
        this.userString = userString;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        requireNonNull(model);
        PartMap pm = model.getPartMap();

        if (pm.contains(userString) == false) {
            throw new CommandException(String.format(MESSAGE_INVAID_PART_REQUESTED, ViewPartCommand.MESSAGE_USAGE));
        } else {
            model.updatePartsMap(); // Require waiting for list
        }

        return new CommandResult(String.format(Messages.MESSAGE_PART_VIEW_OVERVIEW));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewPartCommand // instanceof handles nulls
                && userString.equals(((ViewPartCommand) other).userString)); // state check
    }
}
