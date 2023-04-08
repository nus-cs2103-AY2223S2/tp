package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.doctor.DoctorFilter;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.PatientContainsKeywordsPredicate;
import seedu.address.model.person.patient.PatientFilter;
import seedu.address.testutil.EditDoctorDescriptorBuilder;
import seedu.address.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_YANNIE = "Yannie Lim";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";

    // Doctor specific
    public static final String VALID_SPECIALTY_AMY = "Orthopaedics";
    public static final String VALID_SPECIALTY_BOB = "Paediatrics";
    public static final String VALID_YOE_AMY = "12";
    public static final String VALID_YOE_BOB = "4";

    // Patient specific
    public static final String VALID_HEIGHT_AMY = "1.45";
    public static final String VALID_HEIGHT_BOB = "1.92";
    public static final String VALID_WEIGHT_AMY = "69";
    public static final String VALID_WEIGHT_BOB = "20";
    public static final String VALID_DIAGNOSIS_AMY = "Broken Arm";
    public static final String VALID_DIAGNOSIS_BOB = "Broken Leg";
    public static final String VALID_STATUS_AMY = "Intensive Care Unit";
    public static final String VALID_STATUS_BOB = "Transitional Care";
    public static final String VALID_REMARK_AMY = "some remark amy";
    public static final String VALID_REMARK_BOB = "some remark bob";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    // Doctor Specific
    public static final String SPECIALTY_DESC_AMY = " " + PREFIX_SPECIALTY + VALID_SPECIALTY_AMY;
    public static final String SPECIALTY_DESC_BOB = " " + PREFIX_SPECIALTY + VALID_SPECIALTY_BOB;
    public static final String YOE_DESC_AMY = " " + PREFIX_YOE + VALID_YOE_AMY;
    public static final String YOE_DESC_BOB = " " + PREFIX_YOE + VALID_YOE_BOB;

    // Patient Specific
    public static final String HEIGHT_DESC_AMY = " " + PREFIX_HEIGHT + VALID_HEIGHT_AMY;
    public static final String HEIGHT_DESC_BOB = " " + PREFIX_HEIGHT + VALID_HEIGHT_BOB;
    public static final String WEIGHT_DESC_AMY = " " + PREFIX_WEIGHT + VALID_WEIGHT_AMY;
    public static final String WEIGHT_DESC_BOB = " " + PREFIX_WEIGHT + VALID_WEIGHT_BOB;
    public static final String DIAGNOSIS_DESC_AMY = " " + PREFIX_DIAGNOSIS + VALID_DIAGNOSIS_AMY;
    public static final String DIAGNOSIS_DESC_BOB = " " + PREFIX_DIAGNOSIS + VALID_DIAGNOSIS_BOB;
    public static final String STATUS_DESC_AMY = " " + PREFIX_STATUS + VALID_STATUS_AMY;
    public static final String STATUS_DESC_BOB = " " + PREFIX_STATUS + VALID_STATUS_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;

    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_SPECIALTY_DESC = " " + PREFIX_SPECIALTY + "GP&"; // '&' not allowed in specialty
    public static final String INVALID_YOE_DESC = " " + PREFIX_YOE + "129037"; // YOE maximum 2 digits
    public static final String INVALID_HEIGHT_DESC = " " + PREFIX_HEIGHT + "129037"; // Height should be 2 decimal place
    public static final String INVALID_WEIGHT_DESC = " " + PREFIX_WEIGHT + "34.333"; // Weight maximum 1 decimal place
    public static final String INVALID_DIAGNOSIS_DESC = " " + PREFIX_DIAGNOSIS + "A!DS"; // '!' not allowed in diagnosis
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "hospitalised"; // Wrong status
    public static final String INVALID_REMARK_DESC = " " + PREFIX_REMARK + ""; // Empty remark

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditDoctorCommand.EditDoctorDescriptor DESC_DR_AMY;
    public static final EditDoctorCommand.EditDoctorDescriptor DESC_DR_BOB;
    public static final EditPatientCommand.EditPatientDescriptor DESC_PTN_AMY;
    public static final EditPatientCommand.EditPatientDescriptor DESC_PTN_BOB;



    static {
        DESC_DR_AMY = new EditDoctorDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withSpecialty(VALID_SPECIALTY_AMY)
                .withYoe(VALID_YOE_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_DR_BOB = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withSpecialty(VALID_SPECIALTY_BOB)
                .withYoe(VALID_YOE_BOB).withTags(VALID_TAG_FRIEND).build();
        DESC_PTN_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withHeight(VALID_HEIGHT_AMY)
                .withWeight(VALID_WEIGHT_AMY).withDiagnosis(VALID_DIAGNOSIS_AMY).withStatus(VALID_STATUS_AMY)
                .withRemark(VALID_REMARK_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_PTN_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withHeight(VALID_HEIGHT_BOB)
                .withWeight(VALID_WEIGHT_BOB).withDiagnosis(VALID_DIAGNOSIS_BOB).withStatus(VALID_STATUS_BOB)
                .withRemark(VALID_REMARK_BOB).withTags(VALID_TAG_FRIEND).build();
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage} and a doctor {@code selectedDoctor}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Doctor selectedDoctor, Model expectedModel) {
        CommandResult expectedCommandResult =
                new CommandResult(expectedMessage, selectedDoctor);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage} and a doctor {@code selectedDoctor}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Patient selectedPatient, Model expectedModel) {
        CommandResult expectedCommandResult =
                new CommandResult(expectedMessage, selectedPatient);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().getValue().split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the doctor at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showDoctorAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDoctorList().size());

        Doctor doctor = model.getFilteredDoctorList().get(targetIndex.getZeroBased());
        final String[] splitName = doctor.getName().getValue().split("\\s+");
        model.updateFilteredDoctorList(new DoctorContainsKeywordsPredicate(new DoctorFilter(splitName[0], "",
                "", "", "", new HashSet<>())));

        assertEquals(1, model.getFilteredDoctorList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().getValue().split("\\s+");
        model.updateFilteredPatientList(new PatientContainsKeywordsPredicate(new PatientFilter(splitName[0], "",
                "", "", "", "", "", "", new HashSet<>())));

        assertEquals(1, model.getFilteredPatientList().size());
    }
}
