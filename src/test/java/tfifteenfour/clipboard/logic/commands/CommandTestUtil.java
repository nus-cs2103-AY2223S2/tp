package tfifteenfour.clipboard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_COURSE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_NAME;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_TAG;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.parser.EditCommandParser;
import tfifteenfour.clipboard.logic.predicates.StudentParticularsContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_STUDENTID_AMY = "A9876543Q";
    public static final String VALID_STUDENTID_BOB = "A2345678K";
    public static final String VALID_MODULE_CS2105 = "CS2105";
    public static final String VALID_MODULE_CS2103 = "CS2103";
    public static final String VALID_TAG_TEAM1 = "Team1";
    public static final String VALID_TAG_TEAM2 = "Team2";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String STUDENTID_DESC_AMY = " " + PREFIX_STUDENTID + VALID_STUDENTID_AMY;
    public static final String STUDENTID_DESC_BOB = " " + PREFIX_STUDENTID + VALID_STUDENTID_BOB;
    public static final String MODULE_DESC_CS2105 = " " + PREFIX_COURSE + VALID_MODULE_CS2105;
    public static final String MODULE_DESC_CS2103 = " " + PREFIX_COURSE + VALID_MODULE_CS2103;
    public static final String TAG_DESC_TEAM1 = " " + PREFIX_TAG + VALID_TAG_TEAM1;
    public static final String TAG_DESC_TEAM2 = " " + PREFIX_TAG + VALID_TAG_TEAM2;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_STUDENTID_DESC = " " + PREFIX_STUDENTID; // empty string not allowed for address
    public static final String INVALID_MODULE_DESC = " " + PREFIX_COURSE + "CS*"; // '*' not allowed in module codes
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommandParser.EditStudentDescriptor DESC_AMY;
    public static final EditCommandParser.EditStudentDescriptor DESC_BOB;

    public static final CurrentSelection TEST_CURRENT_SELECTION = new CurrentSelection();

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withStudentId(VALID_STUDENTID_AMY)
                .build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withStudentId(VALID_STUDENTID_BOB)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            System.out.println("expected feedback:" + expectedCommandResult.getFeedbackToUser());
            System.out.println("actual feedback:" + result.getFeedbackToUser());

            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(command, expectedMessage, command.getWillModifyState());
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the roster, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        Roster expectedRoster = actualModel.getRoster().copy();
        List<Student> expectedFilteredList = new ArrayList<>(
                actualModel.getCurrentSelection().getSelectedGroup().getUnmodifiableFilteredStudentList());

        assertThrows(CommandException.class,
                expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRoster, actualModel.getRoster());
        assertEquals(expectedFilteredList,
                actualModel.getCurrentSelection().getSelectedGroup().getUnmodifiableFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased()
                < model.getCurrentSelection().getSelectedGroup().getUnmodifiableFilteredStudentList().size());

        Student student = model.getCurrentSelection().getSelectedGroup()
                .getUnmodifiableFilteredStudentList().get(targetIndex.getZeroBased());
        final String studentId = student.getStudentId().toString();
        String[] tempSidArray = new String[1];
        tempSidArray[0] = studentId;
        model.getCurrentSelection().getSelectedGroup().updateFilteredStudents(
                new StudentParticularsContainsPredicate(tempSidArray));

        assertEquals(1, model.getCurrentSelection().getSelectedGroup()
                .getUnmodifiableFilteredStudentList().size());
    }

}
