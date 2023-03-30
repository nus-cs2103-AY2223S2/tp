package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds and lists unscheduled jobs - those with invalid slot/date
 */
public class TimetableCompletedCommand extends Command {
    public static final CommandGroup COMMAND_GROUP = CommandGroup.TIMETABLE;

    public static final String COMMAND_WORD = "timetable_completed";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists completed jobs";
    public static final String MESSAGE_SUCCESS = "Listed all completed jobs";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true, false, false, false);
    }

}
