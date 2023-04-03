package seedu.event.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.event.model.Model;
import seedu.event.model.event.Event;

/**
 * Calculates and returns the total revenue of events that are marked as done in the event book
 */
public class RevenueCommand extends Command {

    public static final String COMMAND_WORD = "revenue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculates the revenue of all events that are done.";

    public static final String SHOWING_REVENUE_MESSAGE = "The total revenue is: %.2f";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Event> eventList = model.getEventBook().getEventList();
        double revenue = eventList.stream().filter(event -> event.getMark().isDone())
            .mapToDouble(event -> event.getRate().value).sum();
        return new CommandResult(String.format(SHOWING_REVENUE_MESSAGE, revenue));
    }
}
