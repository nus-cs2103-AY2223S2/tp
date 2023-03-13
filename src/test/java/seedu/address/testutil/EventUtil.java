package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.event.Event;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns an add event command string for adding the {@code event}.
     */
    public static String getAddEventCommand(Event event) {
        return AddEventCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_EVENT_NAME + event.getName().name + " ");
        sb.append(PREFIX_START_DATE_TIME + event.getStartDateTime().dateTime + " ");
        sb.append(PREFIX_END_DATE_TIME + event.getEndDateTime().dateTime + " ");
        return sb.toString();
    }
}
