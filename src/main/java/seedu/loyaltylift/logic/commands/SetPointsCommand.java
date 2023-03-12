package seedu.loyaltylift.logic.commands;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.customer.Points;

import static seedu.loyaltylift.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_POINTS;

/**
 * Sets the reward points of a customer
 */
public class SetPointsCommand extends Command {

    public static final String COMMAND_WORD = "setpoints";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets points of the customer identified by the index number used in the displayed customer list. \n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_POINTS + "[POINTS]\n"
            + "Example: " + COMMAND_WORD
            + " 1 "
            + "pt/100";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Points: %2$d";

    private final Index index;
    private final Points points;

    /**
     * @param index of the customer in the filtered person list to set points
     * @param points of the customer to be set
     */
    public SetPointsCommand(Index index, Points points) {
        requireAllNonNull(index, points);

        this.index = index;
        this.points = points;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased(), points));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetPointsCommand)) {
            return false;
        }

        // state check
        SetPointsCommand e = (SetPointsCommand) other;
        return index.equals(e.index)
                && points.equals(e.points);
    }
}
