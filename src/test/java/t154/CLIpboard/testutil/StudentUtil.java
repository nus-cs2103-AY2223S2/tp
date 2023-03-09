package t154.CLIpboard.testutil;

import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_MODULE;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_NAME;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import t154.CLIpboard.logic.commands.AddCommand;
import t154.CLIpboard.logic.commands.EditCommand.EditStudentDescriptor;
import t154.CLIpboard.model.student.ModuleCode;
import t154.CLIpboard.model.student.Student;
import t154.CLIpboard.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_STUDENTID + student.getStudentId().value + " ");
        student.getModules().stream().forEach(
                m -> sb.append(PREFIX_MODULE + m.moduleCode + " ")
        );
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getStudentId().ifPresent(address -> sb.append(PREFIX_STUDENTID).append(address.value).append(" "));

        Set<ModuleCode> modules = descriptor.getModules().get();
        modules.forEach(s -> sb.append(PREFIX_MODULE).append(s.moduleCode).append(" "));

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
