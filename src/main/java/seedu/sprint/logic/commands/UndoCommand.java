package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.model.Model;

/**
 * Reverts the displayed internship book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "The last command, %s, has been undone!\n";

    public static final String MESSAGE_FAILURE = "There is no command to undo!"
            + " The command to be undone needs to previously modified the internship book.";

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoInternshipBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoInternshipBook();
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        String commandToUndo = commandHistory.getPreviousModifyCommand();

        return new CommandResult(String.format(MESSAGE_SUCCESS, commandToUndo));
    }
}
