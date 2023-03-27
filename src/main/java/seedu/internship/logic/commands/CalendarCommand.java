package seedu.internship.logic.commands;

import javafx.collections.ObservableList;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

public class CalendarCommand extends Command {

    public static final String COMMAND_WORD = "calendar";

    public static final String MESSAGE_SHOW_CALENDAR = "Showing calendar ...";

    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredEventList(model.PREDICATE_SHOW_ALL_EVENTS);
        ObservableList<Event> allEvents = model.getFilteredEventList();
        return new CommandResult(MESSAGE_SHOW_CALENDAR, ResultType.CALENDAR, Internship.EMPTY_INTERNSHIP, allEvents);
    }
}
