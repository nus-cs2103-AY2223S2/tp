package seedu.wife.logic.commands.foodcommands;

import static java.util.Objects.requireNonNull;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.model.Model;

/**
 * Sorts food in the WIFE by their expiry date.
 */
public class SortByExpiryCommand extends Command {

    public static final String COMMAND_WORD = "expiry";
    public static final String MESSAGE_SUCCESS = "Food items are being sorted by their expiry dates.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows food sorted by their expiry date.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredFoodListByExpiryDate();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
