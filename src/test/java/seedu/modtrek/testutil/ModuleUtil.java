package seedu.modtrek.testutil;

import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.tag.Tag;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CODE + module.getCode().toString() + " ");
        sb.append(PREFIX_CREDIT + module.getCredit().toString() + " ");
        sb.append(PREFIX_SEMYEAR + module.getSemYear().toString() + " ");
        sb.append(PREFIX_GRADE + module.getGrade().toString() + " ");
        module.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.toString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
     */
    public static String getEditModuleDescriptorDetails(EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCode().ifPresent(name -> sb.append(PREFIX_CODE).append(name.toString()).append(" "));
        descriptor.getCredit().ifPresent(phone -> sb.append(PREFIX_CREDIT).append(phone.toString()).append(" "));
        descriptor.getSemYear().ifPresent(email -> sb.append(PREFIX_SEMYEAR).append(email.toString()).append(" "));
        descriptor.getGrade().ifPresent(address -> sb.append(PREFIX_GRADE).append(address.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (!tags.isEmpty()) {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.toString()).append(" "));
            } else {
                sb.append(PREFIX_TAG).append(" ");
            }
        }
        return sb.toString();
    }

    public static Module copy(Module mod) {
        return new ModuleBuilder(mod).build();
    }
}
