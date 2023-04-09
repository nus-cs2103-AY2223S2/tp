package seedu.internship.logic.commands;

import javafx.collections.ObservableList;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * List all Events for Calendar to view
 */
public class CalendarCommand extends Command {

    public static final String COMMAND_WORD = "calendar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens the calendar page.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SHOW_CALENDAR = "Showing calendar ... \n";
    public static final String MESSAGE_CALENDAR_TIP = "Tip: If there are too many events in a day, you may use "
            + "`event find` command with the date to list all events in that day!\n";

    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredEventList(model.PREDICATE_SHOW_ALL_EVENTS);
        ObservableList<Event> allEvents = model.getFilteredEventList();
        model.updateSelectedInternship(null);
        return new CommandResult(MESSAGE_SHOW_CALENDAR + MESSAGE_CALENDAR_TIP, ResultType.CALENDAR,
                Internship.EMPTY_INTERNSHIP, allEvents);
    }
}
