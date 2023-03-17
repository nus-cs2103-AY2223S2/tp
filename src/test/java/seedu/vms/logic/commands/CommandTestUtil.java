package seedu.vms.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;
import static seedu.vms.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Map;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.logic.commands.patient.EditCommand;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.patient.NameContainsKeywordsPredicate;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_DOB_AMY = "1998-05-23";
    public static final String VALID_DOB_BOB = "2001-01-29";
    public static final String VALID_BLOODTYPE_AMY = "A+";
    public static final String VALID_BLOODTYPE_BOB = "B-";
    public static final String VALID_ALLERGY_SEAFOOD = "seafood";
    public static final String VALID_ALLERGY_GLUTEN = "gluten";
    public static final String VALID_VACCINATION = "moderna";

    public static final String NAME_DESC_AMY = " " + DELIMITER + PREFIX_NAME + " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + DELIMITER + PREFIX_NAME + " " + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + DELIMITER + PREFIX_PHONE + " " + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + DELIMITER + PREFIX_PHONE + " " + VALID_PHONE_BOB;
    public static final String DOB_DESC_AMY = " " + DELIMITER + PREFIX_DOB + " " + VALID_DOB_AMY;
    public static final String DOB_DESC_BOB = " " + DELIMITER + PREFIX_DOB + " " + VALID_DOB_BOB;
    public static final String BLOODTYPE_DESC_AMY = " " + DELIMITER + PREFIX_BLOODTYPE + " " + VALID_BLOODTYPE_AMY;
    public static final String BLOODTYPE_DESC_BOB = " " + DELIMITER + PREFIX_BLOODTYPE + " " + VALID_BLOODTYPE_BOB;
    public static final String ALLERGY_DESC_GLUTEN = " " + DELIMITER + PREFIX_ALLERGY + " " + VALID_ALLERGY_GLUTEN;
    public static final String ALLERGY_DESC_SEAFOOD = " " + DELIMITER + PREFIX_ALLERGY + " " + VALID_ALLERGY_SEAFOOD;
    public static final String VACCINATION_DESC_MODERNA = " " + DELIMITER + PREFIX_VACCINATION + " "
            + VALID_VACCINATION;

    // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + DELIMITER + PREFIX_NAME + " " + "James&";
    // 'a' not allowed in phones
    public static final String INVALID_PHONE_DESC = " " + DELIMITER + PREFIX_PHONE + " " + "911a";
    // Date in the future
    public static final String INVALID_DOB_DESC = " " + DELIMITER + PREFIX_DOB + " " + "2090-03-20";
    // empty string not allowed for blood type
    public static final String INVALID_BLOODTYPE_DESC = " " + DELIMITER + PREFIX_BLOODTYPE;
    // '*' not allowed in allergy
    public static final String INVALID_ALLERGY_DESC = " " + DELIMITER + PREFIX_ALLERGY + " " + "seafood*";
    // '*' not allowed in vaccine
    public static final String INVALID_VACCINE_DESC = " " + DELIMITER + PREFIX_VACCINATION + " " + "moderna*";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPatientDescriptor DESC_AMY;
    public static final EditCommand.EditPatientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withDob(VALID_DOB_AMY).withBloodType(VALID_BLOODTYPE_AMY)
                .withAllergies(VALID_ALLERGY_GLUTEN).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withDob(VALID_DOB_BOB).withBloodType(VALID_BLOODTYPE_BOB)
                .withAllergies(VALID_ALLERGY_SEAFOOD, VALID_ALLERGY_GLUTEN).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandMessage} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandMessage expectedCommandResult,
            Model expectedModel) {
        try {
            CommandMessage result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandMessage, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandMessage expectedCommandResult = new CommandMessage(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the patient manager, filtered patient list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PatientManager expectedPatientManager = new PatientManager(actualModel.getPatientManager());
        Map<Integer, IdData<Patient>> expectedFilteredList = actualModel.getFilteredPatientList();

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPatientManager, actualModel.getPatientManager());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s patient manager.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased()).getValue();
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }

}
