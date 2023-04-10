package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.COURSE_COMMAND_PREFIX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.GROUP_COMMAND_PREFIX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.INVALID_STUDENTID_DESC;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.SESSION_COMMAND_PREFIX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.STUDENT_COMMAND_PREFIX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.TASK_COMMAND_PREFIX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static tfifteenfour.clipboard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tfifteenfour.clipboard.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.commands.addcommand.AddCourseCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddGroupCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddSessionCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddStudentCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddTaskCommand;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.testutil.StudentBuilder;

public class AddCommandParserTest {
    public static final Student BOB = new StudentBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withStudentId(VALID_STUDENTID_BOB)
            .build();

    public static final String COURSE_NAME = "CS2103T";
    public static final String GROUP_NAME = "T15";
    public static final String SESSION_NAME = "T01";
    public static final String TASK_NAME = "OP1";


    public static final Course FIRST_COURSE = new Course(COURSE_NAME);
    public static final Group FIRST_GROUP = new Group(GROUP_NAME);
    public static final Session FIRST_SESSION = new Session(SESSION_NAME);
    public static final Task FIRST_TASK = new Task(TASK_NAME);

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, COURSE_COMMAND_PREFIX + COURSE_NAME, new AddCourseCommand(FIRST_COURSE));
        assertParseSuccess(parser, GROUP_COMMAND_PREFIX + GROUP_NAME, new AddGroupCommand(FIRST_GROUP));
        assertParseSuccess(parser, SESSION_COMMAND_PREFIX + SESSION_NAME,
                new AddSessionCommand(FIRST_SESSION));
        assertParseSuccess(parser, TASK_COMMAND_PREFIX + TASK_NAME, new AddTaskCommand(FIRST_TASK));

        Student expectedStudent = BOB;

        // whitespace only preamble
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + STUDENTID_DESC_BOB, new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser,
                STUDENT_COMMAND_PREFIX + NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + STUDENTID_DESC_BOB,
                new AddStudentCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + STUDENTID_DESC_BOB, new AddStudentCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + STUDENTID_DESC_BOB, new AddStudentCommand(expectedStudent));

        // multiple student ids - last student id accepted
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STUDENTID_DESC_AMY + STUDENTID_DESC_BOB, new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + STUDENTID_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                        + STUDENTID_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                        + STUDENTID_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_STUDENTID_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + STUDENTID_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                        + STUDENTID_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + STUDENTID_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid studentId
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_STUDENTID_DESC,
                StudentId.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_STUDENTID_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                STUDENT_COMMAND_PREFIX + PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + STUDENTID_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
