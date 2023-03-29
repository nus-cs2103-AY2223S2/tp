package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.StateHistory;

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
    public void setHistory(StateHistory history) {
        this.history = history;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(history);
        int redoneCommands = history.redo(numCommands);
        Model redoneModel = history.presentModel();
        model.setAddressBook(redoneModel.getAddressBook());
        model.updateFilteredPersonList(redoneModel.getPredicate());
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
