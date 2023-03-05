package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Views a person's contact details
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": views the contact details of the person identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    private final Index index;

    /**
     * Constructor for viewCommand
     * @param index index of the person in the list to display their contacts
     */
    public ViewCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("view contact details");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand v = (ViewCommand) other;
        return index.equals(v.index);
    }
}
