package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TuteeManagingSystem;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.model.tutee.fields.Lesson;
import seedu.address.model.tutee.fields.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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
    public static final String VALID_REMARK_AMY = "Likes to work";
    public static final String VALID_REMARK_BOB = "Likes to build";
    public static final String VALID_SUBJECT_AMY = "Math";
    public static final String VALID_SUBJECT_BOB = "English";
    public static final String VALID_SCHEDULE_AMY = "friday";
    public static final String VALID_SCHEDULE_BOB = "friday";
    public static final String VALID_STARTTIME_AMY = "10:30";
    public static final String VALID_STARTTIME_BOB = "14:00";
    public static final String VALID_ENDTIME_AMY = "12:30";
    public static final String VALID_ENDTIME_BOB = "16:00";
    public static final String VALID_TAG_EFFORT = "GoodEffort";
    public static final String VALID_TAG_QUICK = "LearnQuick";
    public static final Attendance VALID_ATTENDANCE = new Attendance();
    public static final Lesson VALID_LESSON = new Lesson();


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String SUBJECT_DESC_AMY = " " + PREFIX_SUBJECT + VALID_SUBJECT_AMY;
    public static final String SUBJECT_DESC_BOB = " " + PREFIX_SUBJECT + VALID_SUBJECT_BOB;
    public static final String SCHEDULE_DESC_AMY = " " + PREFIX_SCHEDULE + VALID_SCHEDULE_AMY;
    public static final String SCHEDULE_DESC_BOB = " " + PREFIX_SCHEDULE + VALID_SCHEDULE_BOB;
    public static final String STARTTIME_DESC_AMY = " " + PREFIX_STARTTIME + VALID_STARTTIME_AMY;
    public static final String STARTTIME_DESC_BOB = " " + PREFIX_STARTTIME + VALID_STARTTIME_BOB;
    public static final String ENDTIME_DESC_AMY = " " + PREFIX_ENDTIME + VALID_ENDTIME_AMY;
    public static final String ENDTIME_DESC_BOB = " " + PREFIX_ENDTIME + VALID_ENDTIME_BOB;
    public static final String TAG_DESC_EFFORT = " " + PREFIX_TAG + VALID_TAG_EFFORT;
    public static final String TAG_DESC_QUICK = " " + PREFIX_TAG + VALID_TAG_QUICK;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_SUBJECT_DESC = " " + PREFIX_SUBJECT; // empty subject not allowed for students
    public static final String INVALID_SCHEDULE_DESC = " "
            + PREFIX_SUBJECT + "mon"; // short form for the day not allowed
    public static final String INVALID_STARTTIME_DESC = " " + PREFIX_STARTTIME + "two"; // must be in HH:MM format
    public static final String INVALID_ENDTIME_DESC = " " + PREFIX_ENDTIME; // empty end time not allowed for students
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSubject(VALID_SUBJECT_AMY).withTags(TAG_DESC_EFFORT).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(TAG_DESC_EFFORT, TAG_DESC_QUICK).build();
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
     * Convenience wrapper EndTime {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
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
     * - the address book, filtered tutee list and selected tutee in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable EndTime defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TuteeManagingSystem expectedTuteeManagingSystem = new TuteeManagingSystem(actualModel.getTuteeManagingSystem());
        List<Tutee> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTuteeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTuteeManagingSystem, actualModel.getTuteeManagingSystem());
        assertEquals(expectedFilteredList, actualModel.getFilteredTuteeList());
    }
    /**
     * Updates {@code model}'s filtered list EndTime show only the tutee at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTuteeList().size());

        Tutee tutee = model.getFilteredTuteeList().get(targetIndex.getZeroBased());
        final String[] splitName = tutee.getName().toString().split("\\s+");
        model.updateFilteredTuteeList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTuteeList().size());
    }

}
