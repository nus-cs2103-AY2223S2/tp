package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.jobs.sorters.SortbyTime;

/**
 * Shows timetable of the requested week based on user's date input
 */
public class TimetableDateCommand extends Command {
    public static final String COMMAND_WORD = "timetable";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows timetable of scheduled/uncompleted jobs in the week, "
            + "based on user's input date\n"
            + "Parameters: "
            + PREFIX_DATE + "DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "2023-03-15";

    public static final String SHOWING_TIMETABLE_MESSAGE = "Show timetable of the according week.";

    public static final SortbyTime SORTER = new SortbyTime();
    private final LocalDate jobDate;

    /**
     * Finds and shows timetable of jobs in week based on input date
     * @param jobDate input date
     */
    public TimetableDateCommand(LocalDate jobDate) {
        requireNonNull(jobDate);
        this.jobDate = jobDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateSortedDeliveryJobList(SORTER);
        model.getSortedDeliveryJobListByDate();
        model.updateSortedDeliveryJobListByDate();
        model.updateWeekDeliveryJobList(jobDate);

        //System.out.print(model.getSortedDeliveryJobListByDate());
        return new CommandResult(SHOWING_TIMETABLE_MESSAGE, false, true, false);
    }

}
