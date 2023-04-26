package taa.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import taa.commons.core.index.Index;
import taa.logic.commands.exceptions.CommandException;
import taa.logic.parser.CliSyntax;
import taa.model.ClassList;
import taa.model.Model;
import taa.model.student.NameContainsKeywordsPredicate;
import taa.model.student.Student;
import taa.testutil.Assert;
import taa.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_TAG_TUT_15 = "TUT_15";
    public static final String VALID_TAG_LAB02 = "LAB02";
    public static final String VALID_CLASS_STAT_ATTENDANCE = "attendance";
    public static final String VALID_CLASS_STAT_GRADES = "grades";
    public static final String VALID_ASSIGNMENT_NAME_TEST1 = "test1";


    public static final String NAME_DESC_AMY = " " + CliSyntax.PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + CliSyntax.PREFIX_NAME + VALID_NAME_BOB;
    public static final String TAG_DESC_FRIEND = " " + CliSyntax.PREFIX_CLASS_TAG + VALID_TAG_LAB02;
    public static final String TAG_DESC_HUSBAND = " " + CliSyntax.PREFIX_CLASS_TAG + VALID_TAG_TUT_15;
    public static final String CLASS_STAT_DESC_ATTENDANCE = " "
        + CliSyntax.PREFIX_STAT_TYPE + VALID_CLASS_STAT_ATTENDANCE;
    public static final String CLASS_STAT_DESC_GRADES = " " + CliSyntax.PREFIX_STAT_TYPE + VALID_CLASS_STAT_GRADES;
    public static final String ASSIGNMENT_NAME_DESC_TEST1 = " "
        + CliSyntax.PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_TEST1;

    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TAG_DESC = " "
            + CliSyntax.PREFIX_CLASS_TAG
            + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_CLASS_STAT_ASSIGNMENT = " "
        + CliSyntax.PREFIX_STAT_TYPE + "assignment"; // only 'attendance'/'grades' allowed for class_stats

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditStudentDescriptor DESC_AMY;
    public static final EditStudentCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withTags(VALID_TAG_LAB02).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withTags(VALID_TAG_TUT_15, VALID_TAG_LAB02).build();
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
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ClassList expectedClassList = new ClassList(actualModel.getTaaData().studentList);
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedClassList, actualModel.getTaaData().studentList);
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }
}
