package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.card.Card;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Card.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code card}.
     */
    public static String getAddCommand(Card card) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(card);
    }

    /**
     * Returns the part of command string for the given {@code card}'s details.
     */
    public static String getPersonDetails(Card card) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + card.getQuestion().question + " ");
        sb.append(PREFIX_ANSWER + card.getAnswer().answer + " ");
        card.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_QUESTION).append(name.question).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ANSWER).append(address.answer).append(" "));
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
