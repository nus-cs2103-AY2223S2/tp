package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TANK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDING_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_FED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditFishDescriptor;
import seedu.address.model.fish.Fish;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Fish.
 */
public class FishUtil {

    /**
     * Returns an add command string for adding the {@code fish}.
     */
    public static String getAddCommand(Fish fish) {
        return AddCommand.COMMAND_WORD + " " + getFishDetails(fish);
    }

    /**
     * Returns the part of command string for the given {@code fish}'s details.
     */
    public static String getFishDetails(Fish fish) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TANK + VALID_TANK_INDEX + " ");
        sb.append(PREFIX_NAME + fish.getName().fullName + " ");
        sb.append(PREFIX_LAST_FED_DATE + fish.getLastFedDate().value + " ");
        sb.append(PREFIX_SPECIES + fish.getSpecies().species + " ");
        sb.append(PREFIX_FEEDING_INTERVAL + fish.getFeedingInterval().value + " ");
        sb.append(PREFIX_TANK + "1" + " "); // As of implementing this, Tank does not know its index so need hard code
        fish.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFishDescriptor}'s details.
     */
    public static String getEditFishDescriptorDetails(EditFishDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getLastFedDate().ifPresent(lastFedDate -> sb.append(PREFIX_LAST_FED_DATE).append(lastFedDate.value)
                .append(" "));
        descriptor.getSpecies().ifPresent(species -> sb.append(PREFIX_SPECIES).append(species.species).append(" "));
        descriptor.getFeedingInterval().ifPresent(feedingInterval -> sb.append(PREFIX_FEEDING_INTERVAL)
                .append(feedingInterval.value).append(" "));
        descriptor.getTank().ifPresent(tank -> sb.append(PREFIX_TANK).append(tank.getTankName().fullTankName)
                .append(" "));
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
