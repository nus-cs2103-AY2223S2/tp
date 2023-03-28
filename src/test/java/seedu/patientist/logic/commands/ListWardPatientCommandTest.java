package seedu.patientist.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.patientist.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
//import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.patientist.testutil.TypicalPatients.ADAM;
//import static seedu.patientist.testutil.TypicalPatients.CHARLIE;
//import static seedu.patientist.testutil.TypicalWards.getTypicalPatientist;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.patientist.model.Model;
//import seedu.patientist.model.ModelManager;
//import seedu.patientist.model.UserPrefs;
//import seedu.patientist.model.person.patient.PatientInWardPredicate;

public class ListWardPatientCommandTest {
//    private Model model = new ModelManager(getTypicalPatientist(), new UserPrefs());
//    private Model expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());
//
//    @Test
//    public void equals() {
//        PatientInWardPredicate firstPredicate =
//                new PatientInWardPredicate(Collections.singletonList("first"));
//        PatientInWardPredicate secondPredicate =
//                new PatientInWardPredicate(Collections.singletonList("second"));
//
//        ListWardPatientsCommand findFirstCommand = new ListWardPatientsCommand(firstPredicate);
//        ListWardPatientsCommand findSecondCommand = new ListWardPatientsCommand(secondPredicate);
//
//        // same object -> returns true
//        assertTrue(findFirstCommand.equals(findFirstCommand));
//
//        // same values -> returns true
//        ListWardPatientsCommand findFirstCommandCopy = new ListWardPatientsCommand(firstPredicate);
//        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(findFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(findFirstCommand.equals(null));
//
//        // different ward -> returns false
//        assertFalse(findFirstCommand.equals(findSecondCommand));
//    }
//
//    @Test
//    public void execute_zeroKeywords_noPatientFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        PatientInWardPredicate predicate = preparePredicate(" ");
//        ListWardPatientsCommand command = new ListWardPatientsCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    @Test
//    public void execute_multipleKeywords_multiplePersonsFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
//        PatientInWardPredicate predicate = preparePredicate("Block1WardA Block2WardC Block2WardA");
//        ListWardPatientsCommand command = new ListWardPatientsCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(ADAM, CHARLIE), model.getFilteredPersonList());
//    }
//
//    @Test
//    public void execute_onlyPatients_found() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
//        PatientInWardPredicate predicate = new PatientInWardPredicate(Collections.singletonList("Block2WardA"));
//        ListWardPatientsCommand command = new ListWardPatientsCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CHARLIE), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private PatientInWardPredicate preparePredicate(String userInput) {
//        return new PatientInWardPredicate(Arrays.asList(userInput.split("\\s+")));
//    }
}
