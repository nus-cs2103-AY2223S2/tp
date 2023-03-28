package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.EventCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;

/**
 * List all events with clashing timings.
 */
public class ClashCommand extends Command {

    public static final String COMMAND_WORD = "clash";

    public static final String MESSAGE_CLASH_INTERNSHIP_SUCCESS = "Displaying all clashing events";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        EventCatalogue eventCatalogue = (EventCatalogue) model.getEventCatalogue();
        HashMap<Event, List<Event>> hash = eventCatalogue.findClashEvents();
        return new CommandResult(String.format(MESSAGE_CLASH_INTERNSHIP_SUCCESS), ResultType.CLASH, hash);
    }
}
