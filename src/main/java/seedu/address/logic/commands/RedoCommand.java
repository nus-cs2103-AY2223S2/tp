package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;
import seedu.address.model.StateHistory;
import seedu.address.model.exceptions.InputHistoryTimelineException;

/**
 * Redoes a number of the recently undone {@code Command}s.
 */
public class RedoCommand extends Command {
    //CHECKSTYLE.OFF: VisibilityModifier
    public static List<String> commandWords = new ArrayList<String>(Arrays.asList("redo", "r", "heal"));
    //CHECKSTYLE.ON: VisibilityModifier

    public static final String MESSAGE_USAGE = commandWords + ": Redoes the last undone command,"
            + "or a number of the most recently undone commands.\n"
            + "Parameters: [NUMBER_OF_COMMANDS]...\n"
            + "Example: " + commandWords + " 5";

    public static final String MESSAGE_SUCCESS = "Redone %1$d / %2$d commands";

    private static final Logger logger = LogsCenter.getLogger(RedoCommand.class);

    private final int numCommands;
    private StateHistory history = null;

    /**
     * Creates an RedoCommand to redo a given number of undone Commands.
     *
     * @param numCommands Number of commands to redo
     */
    public RedoCommand(int numCommands) {
        this.numCommands = numCommands;
    }

    @Override
    public void setStateHistory(StateHistory history) {
        this.history = history;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(history);
        int redoneCommands = history.redo(numCommands);
        Model redoneModel = history.presentModel();
        model.replicateStateOf(redoneModel);
        try {
            model.getInputHistory().redo(redoneCommands);
        } catch (InputHistoryTimelineException ex) {
            logger.warning("Input history could not be redone: " + ex.getMessage());
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, redoneCommands, numCommands), false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RedoCommand // instanceof handles nulls
                && numCommands == ((RedoCommand) other).numCommands); // state check
    }
}
