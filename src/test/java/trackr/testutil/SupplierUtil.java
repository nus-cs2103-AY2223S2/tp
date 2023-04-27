package trackr.testutil;

import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import trackr.logic.commands.supplier.AddSupplierCommand;
import trackr.model.commons.Tag;
import trackr.model.person.PersonDescriptor;
import trackr.model.person.Supplier;

/**
 * A utility class for Supplier.
 */
//@@author liumc-sg-reused
public class SupplierUtil {

    /**
     * Returns an add command string for adding the {@code supplier}.
     */
    public static String getAddCommand(Supplier supplier) {
        return AddSupplierCommand.COMMAND_WORD + " " + getsupplierDetails(supplier);
    }

    /**
     * Returns the part of command string for the given {@code Supplier}'s details.
     */
    public static String getsupplierDetails(Supplier supplier) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + supplier.getPersonName().getName() + " ");
        sb.append(PREFIX_PHONE + supplier.getPersonPhone().personPhone + " ");
        sb.append(PREFIX_EMAIL + supplier.getPersonEmail().personEmail + " ");
        sb.append(PREFIX_ADDRESS + supplier.getPersonAddress().personAddress + " ");
        supplier.getPersonTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditSupplierDescriptor}'s details.
     */
    public static String getEditSupplierDescriptorDetails(PersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.getName()).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.personPhone).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.personEmail).append(" "));
        descriptor.getAddress()
                .ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.personAddress).append(" "));
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
