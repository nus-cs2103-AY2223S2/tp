package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.Duration;

import seedu.task.commons.core.Messages;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.task.TaskWithinTimelinePredicate;

/**
 * Sets the timeframe for the alert of the app and displays alert.
 */
public class AlertCommand extends Command {

    public static final String COMMAND_WORD = "alert";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Alerts user about upcoming tasks (by default within a 24 hour timeframe).\n"
            + "Parameters: TIMEFRAME (must be a positive integer representing hours)\n"
            + "Example: " + COMMAND_WORD + " 48";

    public static final String SHOWING_ALERT_MESSAGE = "Your alerts are as follows.";
    public static final String ALERT_COMMAND_SUCCESS = SHOWING_ALERT_MESSAGE;

    private Duration timeframe = Duration.ofHours(24);

    /**
     * Initializes alert to a default of 24 hours.
     */
    public AlertCommand() {
    }

    /**
     * Sets {@code timeframe} to user input in hours.
     */
    public AlertCommand(int timeframe) {
        this.timeframe = Duration.ofHours(timeframe);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.timeframe.isZero() || this.timeframe.isNegative()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DURATION);
        }
        model.updateAlertTaskList(new TaskWithinTimelinePredicate(timeframe));
        return new CommandResult(SHOWING_ALERT_MESSAGE, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AlertCommand // instanceof handles nulls
                && timeframe.equals(((AlertCommand) other).timeframe)); // state check
    }
}
