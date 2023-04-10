package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.jobs.sorters.SortbyTimeAndEarn;

/**
 * Finds and lists unscheduled jobs - those with invalid slot/date
 */
public class TimetableUnscheduleCommand extends Command {
    public static final CommandGroup COMMAND_GROUP = CommandGroup.TIMETABLE;
    public static final SortbyTimeAndEarn SORTER_BY_DATE = new SortbyTimeAndEarn();

    public static final String COMMAND_WORD = "timetable_unscheduled";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists unscheduled jobs with invalid slot/date";
    public static final String MESSAGE_SUCCESS = "Listed all unscheduled jobs with invalid slot/date";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedDeliveryJobList(SORTER_BY_DATE);
        model.getUnscheduledDeliveryJobList();

        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false, false, false);
    }

}
