package seedu.address.testutil;

import static seedu.address.model.entity.Character.CHAR_COMMAND_TERM;
import static seedu.address.model.entity.Item.ITEM_COMMAND_TERM;
import static seedu.address.model.entity.Mob.MOB_COMMAND_TERM;

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
        return "make" + " " + getEntityDetails(entity);
    }

    /**
     * Returns the part of command string for the given {@code entity}'s details.
     */
    public static String getEntityDetails(Entity entity) {
        StringBuilder sb = new StringBuilder();
        if (entity instanceof Character) {
            sb.append(CHAR_COMMAND_TERM).append(" ");
        } else if (entity instanceof Item) {
            sb.append(ITEM_COMMAND_TERM).append(" ");
        } else if (entity instanceof Mob) {
            sb.append(MOB_COMMAND_TERM).append(" ");
        }
        sb.append(entity.getName().fullName).append(" ");
        return sb.toString();
    }

}
