package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.routines.Routine;

/**
 * Deletes a client identified using it's displayed index from the address book.
 */
public class DeleteRoutineCommand extends Command {

    public static final String COMMAND_WORD = "deleteRoutine";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the routine identified by the index number used in the displayed routine list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ROUTINE_SUCCESS = "Deleted Routine: %1$s";

    private final Index targetIndex;

    public DeleteRoutineCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        List<Routine> lastShownList = model.getFilteredRoutineList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX);
        }

        Routine routineToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRoutine(routineToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ROUTINE_SUCCESS, routineToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRoutineCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteRoutineCommand) other).targetIndex)); // state check
    }
}
