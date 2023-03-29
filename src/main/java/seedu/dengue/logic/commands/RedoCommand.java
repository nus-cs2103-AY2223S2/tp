package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;

/**
 * A command that reverses an undo operation.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo successful!";
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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (int i = 0; i < this.numberOfRedos; i++) {
            model.redo();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
