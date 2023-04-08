package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_VALUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Mathutoring;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;



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
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_PARENT_PHONE_AMY = "33333333";
    public static final String VALID_PARENT_PHONE_BOB = "44444444";
    public static final String VALID_TASK_NAME_1 = "Complete E Math Paper 1";
    public static final String VALID_TASK_NAME_2 = "Do Math Exercise 2";
    public static final String VALID_SCORE_LABEL = "Final Assessment 2022";
    public static final String VALID_SCORE_VALUE = "80";
    public static final String VALID_SCORE_DATE = "2023-03-09";
    public static final String INVALID_SCORE_VALUE = "120"; //value exceed 100 is not allowed
    public static final String INVALID_DATE_FORMAT = "2012/04/03"; //date in wrong format
    public static final String DATE_IN_FUTURE = "9999-04-03"; //date in the future



    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PARENT_PHONE_DESC_AMY = " " + PREFIX_PARENT_PHONE + VALID_PARENT_PHONE_AMY;
    public static final String PARENT_PHONE_DESC_BOB = " " + PREFIX_PARENT_PHONE + VALID_PARENT_PHONE_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TASK_NAME_DESC_TASK_1 = " " + PREFIX_TASK_TITLE + VALID_TASK_NAME_1;
    public static final String SCORE_FULL_1 = " " + PREFIX_SCORE_LABEL + VALID_SCORE_LABEL + " " + PREFIX_SCORE_VALUE
            + VALID_SCORE_VALUE + " " + PREFIX_SCORE_DATE + VALID_SCORE_DATE;
    public static final String SCORE_FULL_MISSING_LABEL_PREFIX = " " + VALID_SCORE_LABEL + " " + PREFIX_SCORE_VALUE
            + VALID_SCORE_VALUE + " " + PREFIX_SCORE_DATE + VALID_SCORE_DATE;
    public static final String SCORE_FULL_WRONG_VALUE = " " + PREFIX_SCORE_LABEL + VALID_SCORE_LABEL + " "
            + PREFIX_SCORE_VALUE + INVALID_SCORE_VALUE + " " + PREFIX_SCORE_DATE + VALID_SCORE_DATE;
    public static final String SCORE_FULL_INVALID_DATE_FORMAT = " " + PREFIX_SCORE_LABEL + VALID_SCORE_LABEL + " "
            + PREFIX_SCORE_VALUE + VALID_SCORE_VALUE + " " + PREFIX_SCORE_DATE + INVALID_DATE_FORMAT;
    public static final String SCORE_FULL_DATE_IN_FUTURE = " " + PREFIX_SCORE_LABEL + VALID_SCORE_LABEL + " "
            + PREFIX_SCORE_VALUE + VALID_SCORE_VALUE + " " + PREFIX_SCORE_DATE + DATE_IN_FUTURE;
    public static final String SCORE_MISSING_ALL_VARIABLES = " " + PREFIX_SCORE_LABEL + " "
            + PREFIX_SCORE_VALUE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PARENT_PHONE_DESC = " " + PREFIX_PARENT_PHONE
                                                            + "911#"; // '#' not allowed in phones
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TASK_NAME_DESC = " " + PREFIX_TASK_TITLE + "Do Exercise 1.1"; // '.' not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the math tutoring, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Mathutoring expectedMathutoring = new Mathutoring(actualModel.getMathutoring());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedMathutoring, actualModel.getMathutoring());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s mathutoring.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
