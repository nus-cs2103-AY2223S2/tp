package seedu.dengue.testutil;

import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;

import java.util.Set;

import seedu.dengue.logic.commands.AddCommand;
import seedu.dengue.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.variant.Variant;

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
        sb.append(PREFIX_POSTAL + person.getPostal().value + " ");
        sb.append(PREFIX_DATE + person.getDate().value + " ");
        sb.append(PREFIX_AGE + person.getAge().value + " ");
        person.getVariants().stream().forEach(
            s -> sb.append(PREFIX_VARIANT + s.variantName.toString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPostal().ifPresent(postal -> sb.append(PREFIX_POSTAL).append(postal.value).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.value).append(" "));
        descriptor.getAge().ifPresent(address -> sb.append(PREFIX_AGE).append(address.value).append(" "));
        if (descriptor.getVariants().isPresent()) {
            Set<Variant> variantSet = descriptor.getVariants().get();
            if (variantSet.isEmpty()) {
                sb.append(PREFIX_VARIANT);
            } else {
                variantSet.forEach(s -> sb.append(PREFIX_VARIANT).append(s.variantName).append(" "));
            }
        }
        return sb.toString();
    }
}
