package seedu.internship.logic.commands;

import javafx.collections.ObservableList;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;

import java.time.LocalDate;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Displays the home screen which contains reminders of events and summary of functions.
 */

public class HomeCommand extends Command {

    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the home screen.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HOME_MESSAGE = "Showing home screen.";

    public static final Predicate<Event> PREDICATE_EVENT_REMINDER = event -> event
            .isBetweenDate(LocalDate.now(), LocalDate.now().plusDays(1));

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(PREDICATE_EVENT_REMINDER);
        ObservableList<Event> events = model.getFilteredEventList();

        return new CommandResult(SHOWING_HOME_MESSAGE, ResultType.HOME, events);
    }
}
