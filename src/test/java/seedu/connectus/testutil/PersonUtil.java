package seedu.connectus.testutil;

import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.Set;

import seedu.connectus.logic.commands.AddCommand;
import seedu.connectus.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.Major;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;


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
        if (person.getPhone().isPresent()) {
            sb.append(PREFIX_PHONE + String.valueOf(person.getPhone().get()) + " ");
        }
        if (person.getEmail().isPresent()) {
            sb.append(PREFIX_EMAIL + String.valueOf(person.getEmail().get()) + " ");
        }
        if (person.getAddress().isPresent()) {
            sb.append(PREFIX_ADDRESS + String.valueOf(person.getAddress().get()) + " ");
        }
        person.getRemarks().stream().forEach(
            s -> sb.append(PREFIX_REMARK + s.remarkName + " ")
        );
        person.getModules().stream().forEach(
                s -> sb.append(PREFIX_MODULE + s.moduleName + " ")
        );
        person.getCcas().stream().forEach(
                s -> sb.append(PREFIX_CCA + s.ccaName + " ")
        );
        person.getMajors().stream().forEach(
                s -> sb.append(PREFIX_MAJOR + s.major + " ")
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
        if (descriptor.getRemarks().isPresent()) {
            Set<Remark> remarks = descriptor.getRemarks().get();
            if (remarks.isEmpty()) {
                sb.append(PREFIX_REMARK).append(" ");
            } else {
                remarks.forEach(s -> sb.append(PREFIX_REMARK).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getModules().isPresent()) {
            Set<Module> modules = descriptor.getModules().get();
            if (modules.isEmpty()) {
                sb.append(PREFIX_MODULE).append(" ");
            } else {
                modules.forEach(s -> sb.append(PREFIX_MODULE).append(s.moduleName).append(" "));
            }
        }
        if (descriptor.getCcas().isPresent()) {
            Set<Cca> ccas = descriptor.getCcas().get();
            if (ccas.isEmpty()) {
                sb.append(PREFIX_CCA).append(" ");
            } else {
                ccas.forEach(s -> sb.append(PREFIX_MODULE).append(s.ccaName).append(" "));
            }
        }
        if (descriptor.getMajors().isPresent()) {
            Set<Major> majors = descriptor.getMajors().get();
            if (majors.isEmpty()) {
                sb.append(PREFIX_MAJOR).append(" ");
            } else {
                majors.forEach(s -> sb.append(PREFIX_MODULE).append(s.major).append(" "));
            }
        }
        return sb.toString();
    }
}
