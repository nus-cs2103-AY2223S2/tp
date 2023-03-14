package seedu.fitbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE_NUMBER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.fitbook.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.predicate.NameContainsKeywordsPredicate;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineNameContainsKeywordsPredicate;
import seedu.fitbook.testutil.client.EditClientDescriptorBuilder;
import seedu.fitbook.testutil.routine.EditRoutineDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // For Client
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_WEIGHT_AMY = "23";
    public static final String VALID_WEIGHT_BOB = "26";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_CALORIE_AMY = "2101";
    public static final String VALID_CALORIE_BOB = "2100";
    public static final String VALID_APPOINTMENT_DATE_ONE = "13-01-2020";
    public static final String VALID_APPOINTMENT_DATE_TWO = "14-12-2021";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String WEIGHT_DESC_AMY = " " + PREFIX_WEIGHT + VALID_WEIGHT_AMY;
    public static final String WEIGHT_DESC_BOB = " " + PREFIX_WEIGHT + VALID_WEIGHT_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String CALORIE_DESC_AMY = " " + PREFIX_CALORIE + VALID_CALORIE_AMY;
    public static final String CALORIE_DESC_BOB = " " + PREFIX_CALORIE + VALID_CALORIE_BOB;
    public static final String APPOINTMENT_DESC_DATE_ONE = " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_DATE_ONE;
    public static final String APPOINTMENT_DESC_DATE_TWO = " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_DATE_TWO;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_CALORIE_DESC = " " + PREFIX_CALORIE + "Lol";
    public static final String INVALID_APPOINTMENT_DESC =
            " " + PREFIX_APPOINTMENT + "11a-11-2020"; // 'a' not allowed in appointment
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "g";
    public static final String INVALID_WEIGHT_DESC = " " + PREFIX_WEIGHT + "-23";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditClientDescriptor DESC_AMY;
    public static final EditCommand.EditClientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withAppointments(VALID_APPOINTMENT_DATE_ONE)
                .withCalorie(VALID_CALORIE_AMY).build();
        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withWeight(VALID_WEIGHT_BOB).withGender(VALID_GENDER_BOB)
                .withAppointments(VALID_APPOINTMENT_DATE_ONE, VALID_APPOINTMENT_DATE_TWO)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withCalorie(VALID_CALORIE_BOB).build();
    }

    // For Routine
    public static final String VALID_ROUTINE_NAME_CARDIO = "Cardio";
    public static final String VALID_ROUTINE_NAME_STRENGTH = "Strength";
    public static final String VALID_EXERCISE_PUSHUP = "3x10 Push Ups";
    public static final String VALID_EXERCISE_SITUP = "4x15 Sit ups";
    public static final String VALID_EXERCISE_INDEX = "1";

    public static final String NAME_DESC_ROUTINE_CARDIO = " " + PREFIX_ROUTINE + VALID_ROUTINE_NAME_CARDIO;
    public static final String NAME_DESC_ROUTINE_STRENGTH = " " + PREFIX_ROUTINE + VALID_ROUTINE_NAME_STRENGTH;
    public static final String EXERCISE_DESC_PUSHUP = " " + PREFIX_EXERCISE + VALID_EXERCISE_PUSHUP;
    public static final String EXERCISE_DESC_SITUP = " " + PREFIX_EXERCISE + VALID_EXERCISE_SITUP;
    public static final String EXERCISE_DESC_INDEX = " " + PREFIX_EXERCISE_NUMBER + VALID_EXERCISE_INDEX;

    public static final String INVALID_ROUTINE_NAME_DESC =
            " " + PREFIX_ROUTINE + "HIIT$"; // '$' not allowed in routine names.
    public static final String INVALID_EXERCISE_NAME_DESC =
            " " + PREFIX_EXERCISE + "Situps@"; // '@' not allowed in exercise.
    public static final String INVALID_EXERCISE_INDEX_DESC =
            " " + PREFIX_EXERCISE_NUMBER + "3@"; // '@' not allowed in exercise.

    public static final EditRoutineCommand.EditRoutineDescriptor DESC_CARDIO;
    public static final EditRoutineCommand.EditRoutineDescriptor DESC_STRENGTH;

    static {
        DESC_CARDIO = new EditRoutineDescriptorBuilder().withExercisesIndex(VALID_EXERCISE_INDEX)
                .withExercise(VALID_EXERCISE_PUSHUP).build();
        DESC_STRENGTH = new EditRoutineDescriptorBuilder().withRoutineName(VALID_ROUTINE_NAME_STRENGTH).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualFitBookModel} matches {@code expectedFitBookModel}
     */
    public static void assertCommandSuccess(Command command, FitBookModel actualFitBookModel,
                                            CommandResult expectedCommandResult, FitBookModel expectedFitBookModel) {
        try {
            CommandResult result = command.execute(actualFitBookModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedFitBookModel, actualFitBookModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, FitBookModel, CommandResult, FitBookModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, FitBookModel actualFitBookModel, String expectedMessage,
            FitBookModel expectedFitBookModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualFitBookModel, expectedCommandResult, expectedFitBookModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered client list and selected client in {@code actualFitBookModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, FitBookModel actualFitBookModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FitBook expectedFitBook = new FitBook(actualFitBookModel.getFitBook());
        List<Client> expectedFilteredList = new ArrayList<>(actualFitBookModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualFitBookModel));
        assertEquals(expectedFitBook, actualFitBookModel.getFitBook());
        assertEquals(expectedFilteredList, actualFitBookModel.getFilteredClientList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s FitBook.
     */
    public static void showClientAtIndex(FitBookModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the Routine at the given {@code targetIndex} in the
     * {@code model}'s FitBook.
     */
    public static void showRoutineAtIndex(FitBookModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRoutineList().size());

        Routine routine = model.getFilteredRoutineList().get(targetIndex.getZeroBased());
        final String[] splitRoutineName = routine.getRoutineName().routineName.split("\\s+");
        model.updateFilteredRoutineList(new RoutineNameContainsKeywordsPredicate(Arrays.asList(splitRoutineName[0])));

        assertEquals(1, model.getFilteredRoutineList().size());
    }

}
