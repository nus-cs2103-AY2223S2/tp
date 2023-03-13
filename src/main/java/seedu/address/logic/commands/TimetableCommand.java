package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyDeliveryJobSystem;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.UniqueDeliveryList;
import seedu.address.model.jobs.sorters.SortbyTime;

import java.util.Comparator;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class TimetableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows timetable of scheduled/uncompleted jobs, "
            + "sorted based on increasing timing\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_TIMETABLE_MESSAGE = "Opened timetable window.";

    public static final SortbyTime sorter = new SortbyTime();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateSortedDeliveryJobList(sorter);
        model.getSortedDeliveryJobList();

        return new CommandResult(SHOWING_TIMETABLE_MESSAGE, false, true, false);
    }

}

