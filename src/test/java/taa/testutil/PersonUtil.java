package taa.testutil;

import java.util.Set;

import taa.logic.commands.AddStudentCommand;
import taa.logic.commands.EditCommand;
import taa.logic.parser.CliSyntax;
import taa.model.student.Student;
import taa.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddStudentCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + student.getName().fullName + " ");
        student.getClassTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_CLASS_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditCommand.EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_CLASS_TAG);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_CLASS_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
