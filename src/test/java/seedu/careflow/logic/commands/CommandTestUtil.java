package seedu.careflow.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT_NUMBER;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.careflow.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.careflow.commons.core.index.Index;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.logic.commands.patientcommands.UpdateCommand.EditPatientDescriptor;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.TradeNameContainsKeywordsPredicate;
import seedu.careflow.model.patient.NameContainsKeywordsPredicate;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.testutil.EditPatientDescriptorBuilder;

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
    public static final String VALID_DOB_AMY = "11-12-1988";
    public static final String VALID_DOB_BOB = "25-05-1975";
    public static final String VALID_GENDER_AMY = "female";
    public static final String VALID_GENDER_BOB = "male";
    public static final String VALID_IC_AMY = "T3412576Z";
    public static final String VALID_IC_BOB = "B9023571P";
    public static final String VALID_DRUG_ALLERGY_AMY = "Antiseizure drugs";
    public static final String VALID_DRUG_ALLERGY_BOB = "non-steroidal anti-inflammatory drugs";
    public static final String VALID_EMERGENCY_CONTACT_AMY = "+(65)-1234 5677";
    public static final String VALID_EMERGENCY_CONTACT_BOB = "+(65)-1223 3455";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String DOB_DESC_AMY = " " + PREFIX_DOB + VALID_DOB_AMY;
    public static final String DOB_DESC_BOB = " " + PREFIX_DOB + VALID_DOB_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String IC_DESC_AMY = " " + PREFIX_IC + VALID_IC_AMY;
    public static final String IC_DESC_BOB = " " + PREFIX_IC + VALID_IC_BOB;
    public static final String DRUG_ALLERGY_DESC_AMY = " " + PREFIX_DRUG_ALLERGY + VALID_DRUG_ALLERGY_AMY;
    public static final String DRUG_ALLERGY_DESC_BOB = " " + PREFIX_DRUG_ALLERGY + VALID_DRUG_ALLERGY_BOB;
    public static final String EMERGENCY_CONTACT_DESC_AMY =
            " " + PREFIX_EMERGENCY_CONTACT_NUMBER + VALID_EMERGENCY_CONTACT_AMY;
    public static final String EMERGENCY_CONTACT_DESC_BOB =
            " " + PREFIX_EMERGENCY_CONTACT_NUMBER + VALID_EMERGENCY_CONTACT_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_DOB_DESC = " " + PREFIX_DOB + "20/03/2099"; // future date not allowed for dob
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "boy"; // should be male or female
    public static final String INVALID_IC_DESC = " " + PREFIX_IC + "A12345678"; // IC should end with character
    public static final String INVALID_DRUG_ALLERGY_DESC =
            " " + PREFIX_DRUG_ALLERGY + "asp*irin"; // '*' are not allowed in drug allergy
    public static final String INVALID_EMERGENCY_CONTACT_DESC =
            " " + PREFIX_EMERGENCY_CONTACT_NUMBER
                    + "+(65)-1111-2222-3333-4444"; // length of phone number shouldn't exceed 20

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPatientDescriptor DESC_AMY;
    public static final EditPatientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withDob(VALID_DOB_AMY).withGender(VALID_GENDER_AMY).withIc(VALID_IC_AMY)
                .withDrugAllergy(VALID_DRUG_ALLERGY_AMY).withEmergencyContact(VALID_EMERGENCY_CONTACT_AMY).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withDob(VALID_DOB_BOB).withGender(VALID_GENDER_BOB).withIc(VALID_IC_BOB)
                .withDrugAllergy(VALID_DRUG_ALLERGY_BOB).withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, CareFlowModel actualModel,
                                            CommandResult expectedCommandResult, CareFlowModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, CareFlowModel, CommandResult, CareFlowModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, CareFlowModel actualModel, String expectedMessage,
                                            CareFlowModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the CareFlow, filtered person list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertPatientCommandFailure(Command command, CareFlowModel actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PatientRecord expectedPatientRecord = new PatientRecord(actualModel.getPatientRecord());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPatientRecord, actualModel.getPatientRecord());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the CareFlow, filtered drug list and selected drug in {@code actualModel} remain unchanged
     */
    public static void assertDrugCommandFailure(Command command, CareFlowModel actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        DrugInventory expectedDrugRecord = new DrugInventory(actualModel.getDrugInventory());
        List<Drug> expectedFilteredList = new ArrayList<>(actualModel.getFilteredDrugList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedDrugRecord, actualModel.getDrugInventory());
        assertEquals(expectedFilteredList, actualModel.getFilteredDrugList());
    }

    /**
     * Updates {@code CareFlowModel}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s CareFlow.
     */
    public static void showPatientAtIndex(CareFlowModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient person = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }

    /**
     * Updates {@code CareFlowModel}'s filtered list to show only the drug at the given {@code targetIndex} in the
     * {@code model}'s CareFlow.
     */
    public static void showDrugAtIndex(CareFlowModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDrugList().size());

        Drug drug = model.getFilteredDrugList().get(targetIndex.getZeroBased());
        final String[] splitName = drug.getTradeName().tradeName.split("\\s+");
        model.updateFilteredDrugList(new TradeNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredDrugList().size());
    }
}

