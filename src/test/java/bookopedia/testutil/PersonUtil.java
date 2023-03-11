package bookopedia.testutil;

import static bookopedia.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static bookopedia.logic.parser.CliSyntax.PREFIX_EMAIL;
import static bookopedia.logic.parser.CliSyntax.PREFIX_NAME;
import static bookopedia.logic.parser.CliSyntax.PREFIX_PARCEL;
import static bookopedia.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;

import bookopedia.logic.commands.AddCommand;
import bookopedia.logic.commands.EditCommand.EditPersonDescriptor;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getParcels().stream().forEach(
            s -> sb.append(PREFIX_PARCEL + s.parcelName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getParcels().isPresent()) {
            Set<Parcel> parcels = descriptor.getParcels().get();
            if (parcels.isEmpty()) {
                sb.append(PREFIX_PARCEL);
            } else {
                parcels.forEach(s -> sb.append(PREFIX_PARCEL).append(s.parcelName).append(" "));
            }
        }
        return sb.toString();
    }
}
