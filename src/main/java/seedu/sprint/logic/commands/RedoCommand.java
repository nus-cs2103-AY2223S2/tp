package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.model.Model;

/**
 * Reverts the {@code model}'s sprint book to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "The last command, %s, has been redone!\n";
    public static final String MESSAGE_FAILURE = "There is no command to redo!"
            + " The command to be redone needs to previously modified the internship book.";

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoInternshipBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoInternshipBook();
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        String commandToRedo = commandHistory.getNextModifyCommand();

        return new CommandResult(String.format(MESSAGE_SUCCESS, commandToRedo));
    }
}
