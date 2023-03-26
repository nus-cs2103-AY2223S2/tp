package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddEntityCommand;
import seedu.address.model.entity.Entity;


/**
 * A utility class for Entity.
 */
public class EntityUtil {

    /**
     * Returns an add command string for adding the {@code entity}.
     */
    public static String getAddEntityCommand(Entity entity) {
        return AddEntityCommand.COMMAND_WORD + " " + getEntityDetails(entity);
    }

    /**
     * Returns the part of command string for the given {@code entity}'s details.
     */
    public static String getEntityDetails(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + entity.getName().fullName + " ");
        entity.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

}
