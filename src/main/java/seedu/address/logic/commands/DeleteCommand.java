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
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified application from the list of internships applied.\n"
            + "Deletes the application of internship at the specified INDEX.\n"
            + "The index refers to the index number shown in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer 1, 2, 3, ...)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_DELETE_APPLICATION_SUCCESS = "Deleted Application: %1$s";

    public static final String MESSAGE_DELETE_CONFIRMATION = "Are you sure you want to delete this: application-%1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Person internshipToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInternship(internshipToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPLICATION_SUCCESS, internshipToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
