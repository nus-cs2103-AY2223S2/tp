package seedu.wife.testutil;

import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.wife.logic.commands.tagcommands.CreateTagCommand;
import seedu.wife.model.tag.Tag;

/**
 * A utility class for Tag.
 */
public class TagUtil {
    /**
     * Returns an add command string for adding the {@code tag}.
     */
    public static String getCreateTagCommand(Tag tag) {
        return CreateTagCommand.COMMAND_WORD + " " + getTagDetails(tag);
    }

    /**
     * Returns the part of command string for the given {@code tag}'s details.
     */
    public static String getTagDetails(Tag tag) {
        return String.format("%s%s", PREFIX_NAME, tag.getTagName());
    }
}
