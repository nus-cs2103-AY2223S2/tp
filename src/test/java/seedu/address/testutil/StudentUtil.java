package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAILSTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTAGE;

import seedu.address.logic.commands.student.StudentAddCommand;
import seedu.address.model.person.student.Student;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getStudentAddCommand(Student student) {
        return "student " + student.getStudentClass().toString() + " "
                + StudentAddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_INDEXNUMBER + student.getIndexNumber().toString() + " ");
        sb.append(PREFIX_PARENTNAME + "Tan ah Cow" + " ");
        sb.append(PREFIX_PHONEPARENT + "91234567" + " ");
        sb.append(PREFIX_RELATIONSHIP + "father" + " ");
        sb.append(PREFIX_SEX + student.getSex().toString() + " ");
        sb.append(PREFIX_ADDRESS + student.getAddress().value + " ");
        sb.append(PREFIX_STUDENTAGE + student.getAge().value + " ");
        sb.append(PREFIX_EMAILSTUDENT + student.getEmail().toString() + " ");
        sb.append(PREFIX_PHONESTUDENT + student.getPhone().toString() + " ");
        sb.append(PREFIX_CCA + student.getCca().value + " ");
        sb.append(PREFIX_ATTENDANCE + "01/01/2019" + " ");
        return sb.toString();
    }
}
