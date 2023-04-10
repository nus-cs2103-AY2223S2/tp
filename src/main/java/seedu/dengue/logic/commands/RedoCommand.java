package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;

/**
 * A command that reverses an undo operation.
 */
public class RedoCommand extends UndoRedoCommand {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redid %s operations successfully!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " performs a redo operation, where an optional argument \n"
            + "can be provided to indicate the number of operations. \n"
            + "eg. redo 2";

    private final int numberOfRedos;

    /**
     * Creates a {@code RedoCommand} to redo previous actions by the user.
     * @param numberOfRedos The number of steps to redo.
     */
    public RedoCommand(int numberOfRedos) {
        this.numberOfRedos = numberOfRedos;
    }
    public RedoCommand() {
        this(1);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.redo();
        int counts = 1 + undoOrRedoAtMost(model, this.numberOfRedos - 1, false);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, counts));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RedoCommand)) {
            return false;
        }

        // state check
        RedoCommand redo = (RedoCommand) other;
        return this.numberOfRedos == redo.numberOfRedos;
    }

}
