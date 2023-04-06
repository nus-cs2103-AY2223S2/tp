package seedu.careflow.logic.commands.patientcommands;

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
import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.logic.commands.patientcommands.UpdateCommand.EditPatientDescriptor;
import seedu.careflow.model.CareFlow;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.patient.Name;
import seedu.careflow.model.patient.NameContainsKeywordsPredicate;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.patient.Phone;
import seedu.careflow.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class PatientCommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_BIRTHDATE_AMY = "01-01-1990";
    public static final String VALID_BIRTHDATE_BOB = "02-02-1990";
    public static final String VALID_GENDER_AMY = "female";
    public static final String VALID_GENDER_BOB = "male";
    public static final String VALID_IC_AMY = "A7654321B";
    public static final String VALID_IC_BOB = "A7654321C";
    public static final String VALID_DRUG_ALLERGY_AMY = "penicillin";
    public static final String VALID_DRUG_ALLERGY_BOB = "panadol";
    public static final String VALID_EMERGENCY_CONTACT_AMY = "88888888";
    public static final String VALID_EMERGENCY_CONTACT_BOB = "77777777";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String BIRTHDATE_DESC_AMY = " " + PREFIX_DOB + VALID_BIRTHDATE_AMY;
    public static final String BIRTHDATE_DESC_BOB = " " + PREFIX_DOB + VALID_BIRTHDATE_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String IC_DESC_AMY = " " + PREFIX_IC + VALID_IC_AMY;
    public static final String IC_DESC_BOB = " " + PREFIX_IC + VALID_IC_BOB;
    public static final String DRUG_ALLERGY_DESC_AMY = " " + PREFIX_DRUG_ALLERGY + VALID_DRUG_ALLERGY_AMY;
    public static final String DRUG_ALLERGY_DESC_BOB = " " + PREFIX_DRUG_ALLERGY + VALID_DRUG_ALLERGY_BOB;
    public static final String EMERGENCY_CONTACT_DESC_AMY = " " + PREFIX_EMERGENCY_CONTACT_NUMBER
            + VALID_EMERGENCY_CONTACT_AMY;
    public static final String EMERGENCY_CONTACT_DESC_BOB = " " + PREFIX_EMERGENCY_CONTACT_NUMBER
            + VALID_EMERGENCY_CONTACT_BOB;

    // TODO ADD MORE CONSTRAINS HERE
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for careflow
    public static final String INVALID_DOB_DESC = " " + PREFIX_DOB + "20/03/2099"; // future date not allowed for dob
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "boy"; // should be male or female
    public static final String INVALID_IC_DESC = " " + PREFIX_IC + "A12345678"; // IC should end with character
    public static final String INVALID_DRUG_ALLERGY_DESC =
            " " + PREFIX_DRUG_ALLERGY + "asp*irin"; // '*' are not allowed in drug allergy
    public static final String INVALID_EMERGENCY_CONTACT_DESC =
            " " + PREFIX_EMERGENCY_CONTACT_NUMBER
                    + "11112222333-34444"; // length of phone number shouldn't contain special symbol

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPatientDescriptor DESC_AMY;
    public static final EditPatientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withDob(VALID_BIRTHDATE_AMY).withGender(VALID_GENDER_AMY).withIc(VALID_IC_AMY)
                .withDrugAllergy(VALID_DRUG_ALLERGY_AMY).withEmergencyContact(VALID_EMERGENCY_CONTACT_AMY)
                .build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withDob(VALID_BIRTHDATE_BOB).withGender(VALID_GENDER_BOB).withIc(VALID_IC_BOB)
                .withDrugAllergy(VALID_DRUG_ALLERGY_BOB).withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualCareFlowModel} matches {@code expectedCareFlowModel}
     */
    public static void assertCommandSuccess(Command command, CareFlowModel actualCareFlowModel,
                                            CommandResult expectedCommandResult, CareFlowModel expectedCareFlowModel) {
        try {
            CommandResult result = command.execute(actualCareFlowModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(actualCareFlowModel.getDrugInventory(), expectedCareFlowModel.getDrugInventory());
            assertEquals(actualCareFlowModel.getPatientRecord(), expectedCareFlowModel.getPatientRecord());
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, CareFlowModel, CommandResult, CareFlowModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, CareFlowModel actualCareFlowModel, String expectedMessage,
                                            CareFlowModel expectedCareFlowModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualCareFlowModel, expectedCommandResult, expectedCareFlowModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the careflow book, filtered person list and selected person in {@code actualCareFlowModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, CareFlowModel actualCareFlowModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        CareFlow expectedCareFlow = new CareFlow(actualCareFlowModel.getPatientRecord(),
                actualCareFlowModel.getDrugInventory());
        // follow default hospital list of CareFlow
        expectedCareFlow.addHospital(new Hospital(new Name("KK Women's and Children's Hospital"),
                new Phone("62255554")));
        expectedCareFlow.addHospital(new Hospital(new Name("Changi General Hospital"), new Phone("67888833")));
        expectedCareFlow.addHospital(new Hospital(new Name("Khoo Teck Puat Hospital"), new Phone("65558000")));
        expectedCareFlow.addHospital(new Hospital(new Name("Tan Tock Seng Hospital"), new Phone("62566011")));

        List<Patient> expectedFilteredList = new ArrayList<>(actualCareFlowModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualCareFlowModel));
        // made some amendment to comparing expectedCareflow and actualCareFlowModel
        assertEquals(expectedCareFlow.getPatientRecord(), actualCareFlowModel.getPatientRecord());
        assertEquals(expectedCareFlow.getDrugInventory(), actualCareFlowModel.getDrugInventory());
        assertEquals(expectedCareFlow.getHospitalRecords(), actualCareFlowModel.getHospitalRecords());
        assertEquals(expectedFilteredList, actualCareFlowModel.getFilteredPatientList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s careflow book.
     */
    public static void showPatientAtIndex(CareFlowModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient person = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }
}
