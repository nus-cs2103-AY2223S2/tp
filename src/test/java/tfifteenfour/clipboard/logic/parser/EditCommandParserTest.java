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
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.MESSAGE_INVALID_INDEX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.SESSION_COMMAND_PREFIX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.STUDENT_COMMAND_PREFIX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.TASK_COMMAND_PREFIX;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static tfifteenfour.clipboard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tfifteenfour.clipboard.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tfifteenfour.clipboard.logic.parser.EditCommandParser.WRONG_PAGE_MESSAGE;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.editcommand.EditCourseCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditGroupCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditSessionCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditStudentCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditTaskCommand;
import tfifteenfour.clipboard.logic.parser.EditCommandParser.EditStudentDescriptor;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.testutil.EditStudentDescriptorBuilder;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class EditCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT,
            EditStudentCommand.MESSAGE_USAGE);
    private Model model;
    private CurrentSelection currentSelection;
    private Course selectedCourse;

    private Group selectedGroup;
    private Session selectedSession;
    private Student selectedStudent;
    private CurrentSelection actualSelection;

    private EditCommandParser parser;

    @BeforeEach
    public void setUp() {
        model = new TypicalModel().getTypicalModel();
        currentSelection = model.getCurrentSelection();
        parser = new EditCommandParser(currentSelection);

        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);
    }

    @Test
    public void parse_missingParts_failure() {
        currentSelection.setCurrentPage(PageType.STUDENT_PAGE);
        // no field specified
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        currentSelection.setCurrentPage(PageType.STUDENT_PAGE);

        // negative index
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "-5" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "0" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

    }

    @Test
    public void parse_invalidValue_failure() {
        currentSelection.setCurrentPage(PageType.STUDENT_PAGE);
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "1" + INVALID_STUDENTID_DESC, StudentId.MESSAGE_CONSTRAINTS); // invalid sid

        // invalid phone followed by valid email
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, STUDENT_COMMAND_PREFIX + "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_STUDENTID_AMY
                + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        currentSelection.setCurrentPage(PageType.STUDENT_PAGE);

        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + STUDENTID_DESC_AMY + NAME_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withStudentId(VALID_STUDENTID_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        currentSelection.setCurrentPage(PageType.STUDENT_PAGE);

        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        currentSelection.setCurrentPage(PageType.STUDENT_PAGE);

        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);

        // student id
        userInput = targetIndex.getOneBased() + STUDENTID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENTID_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        currentSelection.setCurrentPage(PageType.STUDENT_PAGE);

        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + STUDENTID_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_AMY + STUDENTID_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + STUDENTID_DESC_BOB + EMAIL_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withStudentId(VALID_STUDENTID_BOB).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        currentSelection.setCurrentPage(PageType.STUDENT_PAGE);

        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + STUDENTID_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withStudentId(VALID_STUDENTID_BOB).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, STUDENT_COMMAND_PREFIX + userInput, expectedCommand);
    }

    @Test
    public void parse_nonStudent_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput;


        currentSelection.setCurrentPage(PageType.COURSE_PAGE);
        userInput = COURSE_COMMAND_PREFIX + targetIndex.getOneBased() + " " + "CS1101S";
        Course newCourse = new Course("CS1101S");
        EditCourseCommand expectedCourseCommand = new EditCourseCommand(targetIndex, newCourse);
        assertParseSuccess(parser, userInput, expectedCourseCommand);


        currentSelection.setCurrentPage(PageType.GROUP_PAGE);
        userInput = GROUP_COMMAND_PREFIX + targetIndex.getOneBased() + " " + "T20";
        Group newGroup = new Group("T20");
        EditGroupCommand expectedGroupCommand = new EditGroupCommand(targetIndex, newGroup);
        assertParseSuccess(parser, userInput, expectedGroupCommand);

        currentSelection.setCurrentPage(PageType.SESSION_PAGE);
        userInput = SESSION_COMMAND_PREFIX + targetIndex.getOneBased() + " " + "Tutorial1";
        Session newSession = new Session("Tutorial1");
        EditSessionCommand expectedSessionCommand = new EditSessionCommand(targetIndex, newSession);
        assertParseSuccess(parser, userInput, expectedSessionCommand);

        currentSelection.setCurrentPage(PageType.TASK_PAGE);
        userInput = TASK_COMMAND_PREFIX + targetIndex.getOneBased() + " " + "CA1";
        Task newTask = new Task("CA1");
        EditTaskCommand expectedTaskCommand = new EditTaskCommand(targetIndex, newTask);
        assertParseSuccess(parser, userInput, expectedTaskCommand);

    }

    @Test
    public void parse_onWrongPage_failure() {
        currentSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        assertParseFailure(parser, COURSE_COMMAND_PREFIX + "1 CS1101S",
                String.format(WRONG_PAGE_MESSAGE, "course"));
        assertParseFailure(parser, GROUP_COMMAND_PREFIX + "1 CS1101S",
                String.format(WRONG_PAGE_MESSAGE, "group"));
        assertParseFailure(parser, SESSION_COMMAND_PREFIX + "1 CS1101S",
                String.format(WRONG_PAGE_MESSAGE, "session"));



    }
}
