package seedu.event.testutil;

import static seedu.event.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.event.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.event.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.util.Set;

import seedu.event.logic.commands.AddCommand;
import seedu.event.logic.commands.EditCommand;
import seedu.event.model.event.Event;
import seedu.event.model.tag.Tag;

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
        sb.append(PREFIX_RATE + Double.toString(event.getRate().value) + " ");
        sb.append(PREFIX_ADDRESS + event.getAddress().value + " ");
        sb.append(PREFIX_TIME_START + event.getStartTime().toString() + " ");
        sb.append(PREFIX_TIME_END + event.getEndTime().toString() + " ");
        event.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEventDescriptor}'s details.
     */
    public static String getEditEventDescriptorDetails(EditCommand.EditEventDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getRate().ifPresent(rate -> sb.append(PREFIX_RATE).append(rate.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getStartTime().ifPresent(startTime -> sb.append(PREFIX_TIME_START)
                .append(startTime).append(" "));
        descriptor.getEndTime().ifPresent(endTime -> sb.append(PREFIX_TIME_END)
                .append(endTime).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
