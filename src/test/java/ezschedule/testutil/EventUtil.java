package ezschedule.testutil;

import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_END;
import static ezschedule.logic.parser.CliSyntax.PREFIX_NAME;
import static ezschedule.logic.parser.CliSyntax.PREFIX_START;

import ezschedule.logic.commands.AddCommand;
import ezschedule.logic.commands.EditCommand.EditEventDescriptor;
import ezschedule.logic.commands.FindCommand.FindEventDescriptor;
import ezschedule.model.event.Event;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns an add command string for adding the {@code event}.
     */
    public static String getAddCommand(Event event) {
        return AddCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getName().fullName + " ");
        sb.append(PREFIX_DATE + event.getDate().toString() + " ");
        sb.append(PREFIX_START + event.getStartTime().toString() + " ");
        sb.append(PREFIX_END + event.getEndTime().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEventDescriptor}'s details.
     */
    public static String getEditEventDescriptorDetails(EditEventDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date).append(" "));
        descriptor.getStartTime().ifPresent(startTime -> sb.append(PREFIX_START).append(startTime).append(" "));
        descriptor.getEndTime().ifPresent(endTime -> sb.append(PREFIX_END).append(endTime).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code FindEventDescriptor}'s details.
     */
    public static String getFindEventDescriptorDetails(FindEventDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date).append(" "));
        return sb.toString();
    }
}
