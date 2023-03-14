package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPetDescriptor;
import seedu.address.model.pet.Pet;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Pet.
 */
public class PetUtil {

    /**
     * Returns an add command string for adding the {@code pet}.
     */
    public static String getAddCommand(Pet pet) {
        return AddCommand.COMMAND_WORD + " " + getPetDetails(pet);
    }

    /**
     * Returns the part of command string for the given {@code pet}'s details.
     */
    public static String getPetDetails(Pet pet) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + pet.getOwnerName().fullName + " ");
        sb.append(PREFIX_NAME + pet.getName().fullName + " ");
        sb.append(PREFIX_PHONE + pet.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + pet.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + pet.getAddress().value + " ");
        pet.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPetDescriptor}'s details.
     */
    public static String getEditPetDescriptorDetails(EditPetDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getOwnerName().ifPresent(ownerName -> sb.append(PREFIX_NAME).append(ownerName.fullName).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
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
