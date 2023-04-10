package seedu.internship.testutil;

import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_END;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_START;

import seedu.internship.logic.commands.event.EventAddCommand;
import seedu.internship.logic.commands.event.EventCommand;
import seedu.internship.model.event.Event;

/**
 * Util commands required for events tests.
 */
public class EventUtil {

    /**
     * Returns an add command string for adding the {@code Event}.
     */
    public static String getEventAddCommand(Event event) {
        return EventCommand.COMMAND_WORD + " " + EventAddCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_EVENT_NAME + event.getName().name + " ");
        sb.append(PREFIX_EVENT_START + event.getStart().getNumericDateTimeString() + " ");
        sb.append(PREFIX_EVENT_END + event.getEnd().getNumericDateTimeString() + " ");
        sb.append(PREFIX_EVENT_DESCRIPTION + event.getDescription().descriptionMessage + " ");

        return sb.toString();
    }

}
