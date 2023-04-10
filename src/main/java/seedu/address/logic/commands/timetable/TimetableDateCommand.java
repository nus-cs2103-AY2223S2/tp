package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.jobs.sorters.SortbyTimeAndEarn;

/**
 * Shows timetable of the requested week based on user's date input
 */
public class TimetableDateCommand extends Command {
    public static final CommandGroup COMMAND_GROUP = CommandGroup.TIMETABLE;

    public static final String COMMAND_WORD = "timetable_date";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows timetable of scheduled/uncompleted jobs in the week, "
            + "based on user's input date\n"
            + "Parameters: "
            + PREFIX_DATE + "DATE "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_DATE + "2023-03-15";

    public static final String SHOWING_TIMETABLE_MESSAGE = "Showed timetable of the week containing day: %s.";

    public static final SortbyTimeAndEarn SORTER = new SortbyTimeAndEarn();
    private final LocalDate jobDate;

    /**
     * Updates and shows timetable of jobs in week based on input date
     * @param jobDate input date
     */
    public TimetableDateCommand(LocalDate jobDate) {
        requireNonNull(jobDate);
        this.jobDate = jobDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFocusDate(jobDate);
        model.updateSortedDeliveryJobList(SORTER);
        model.updateSortedDeliveryJobListByDate();
        model.updateWeekDeliveryJobList(jobDate);

        String showTimetableMessage = String.format(SHOWING_TIMETABLE_MESSAGE, jobDate.toString());
        CommandResult commandResult = new CommandResult(showTimetableMessage, false, true, false, false, false);
        commandResult.setShowTimetableDate(true);
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimetableDateCommand // instanceof handles nulls
                && jobDate.equals(((TimetableDateCommand) other).jobDate));
    }

}
