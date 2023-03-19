package seedu.wife.testutil;

import static seedu.wife.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_UNIT;

import java.util.Set;

import seedu.wife.logic.commands.foodcommands.AddCommand;
import seedu.wife.logic.commands.foodcommands.EditCommand.EditFoodDescriptor;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class FoodUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Food food) {
        return AddCommand.COMMAND_WORD + " " + getFoodDetails(food);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getFoodDetails(Food food) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + food.getName().toString() + " ");
        sb.append(PREFIX_UNIT + food.getUnit().toString() + " ");
        sb.append(PREFIX_QUANTITY + food.getQuantity().toString() + " ");
        sb.append(PREFIX_EXPIRY_DATE + food.getExpiryDate().toString() + " ");
        food.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditFoodDescriptorDetails(EditFoodDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.toString()).append(" "));
        descriptor.getUnit().ifPresent(unit -> sb.append(PREFIX_UNIT).append(unit.toString()).append(" "));
        descriptor.getQuantity().ifPresent(quantity -> sb.append(PREFIX_QUANTITY)
                .append(quantity.toString()).append(" "));
        descriptor.getExpiryDate().ifPresent(expiryDate -> sb.append(PREFIX_EXPIRY_DATE)
                .append(expiryDate.toString()).append(" "));
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
