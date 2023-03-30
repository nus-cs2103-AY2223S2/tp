package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.jobs.sorters.SortbyTimeAndEarn;

/**
 * Formats full timetable instructions for every command for display.
 */
public class TimetableCommand extends Command {
    public static final CommandGroup COMMAND_GROUP = CommandGroup.TIMETABLE;

    public static final String COMMAND_WORD = "timetable";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows timetable of scheduled/uncompleted jobs, "
            + "sorted based on increasing timing\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_TIMETABLE_MESSAGE = "Opened timetable window of current week.";

    public static final SortbyTimeAndEarn SORTER = new SortbyTimeAndEarn();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFocusDate(LocalDate.now());
        model.updateSortedDeliveryJobList(SORTER);
        model.updateSortedDeliveryJobListByDate();
        model.updateWeekDeliveryJobList(LocalDate.now());

        return new CommandResult(SHOWING_TIMETABLE_MESSAGE, false, true, false, false, false);
    }
}

