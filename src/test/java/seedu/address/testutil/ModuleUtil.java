package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

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
        sb.append(PREFIX_NAME + module.getName().fullName + " ");
        sb.append(PREFIX_ADDRESS + module.getAddress().value + " ");
        sb.append(PREFIX_TIMESLOT + module.getTimeSlot().toString() + " ");
        sb.append(PREFIX_REMARK + module.getRemark().value + " ");
        sb.append(PREFIX_DEADLINE + module.getDeadline().toString() + " ");
        sb.append(PREFIX_TEACHER + module.getTeacher().value + " ");
        sb.append(PREFIX_RESOURCE + module.getResource().value + " ");
        module.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
     */
    public static String getEditModuleDescriptorDetails(EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getTimeSlot().ifPresent(timeSlot -> sb.append(PREFIX_TIMESLOT)
                .append(timeSlot.toString()).append(" "));
        descriptor.getRemark().ifPresent(type -> sb.append(PREFIX_REMARK).append(type.value).append(" "));
        descriptor.getDeadline().ifPresent(type -> sb.append(PREFIX_DEADLINE).append(type.toString()).append(" "));
        descriptor.getTeacher().ifPresent(type -> sb.append(PREFIX_TEACHER).append(type.value).append(" "));
        descriptor.getResource().ifPresent(type -> sb.append(PREFIX_RESOURCE).append(type.value).append(" "));
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
