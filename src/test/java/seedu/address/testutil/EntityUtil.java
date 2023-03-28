package seedu.address.testutil;

import static seedu.address.logic.parser.ClassificationTerms.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.MakeCommand;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;


/**
 * A utility class for Entity.
 */
public class EntityUtil {

    /**
     * Returns an add command string for adding the {@code entity}.
     */
    public static String getMakeCommand(Entity entity) {
        return MakeCommand.COMMAND_WORD + " " + getEntityDetails(entity);
    }

    /**
     * Returns the part of command string for the given {@code entity}'s details.
     */
    public static String getEntityDetails(Entity entity) {
        StringBuilder sb = new StringBuilder();
        if (entity instanceof Character) {
            sb.append(CHAR.label).append(" ");
        } else if (entity instanceof Item) {
            sb.append(ITEM.label).append(" ");
        } else if (entity instanceof Mob) {
            sb.append(MOB.label).append(" ");
        }
        sb.append(entity.getName().fullName).append(" ");
        /*
        entity.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
         */
        return sb.toString();
    }

}
