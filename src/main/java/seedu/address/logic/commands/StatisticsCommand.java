package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Shows summary of descriptive statistics to user.
 */
public class StatisticsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows summary of jobs";

    public static final String SHOWING_STATISTICS_MESSAGE = "Opened statistics window.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        return new CommandResult(SHOWING_STATISTICS_MESSAGE, false, false, false, true, false);
    }

}
