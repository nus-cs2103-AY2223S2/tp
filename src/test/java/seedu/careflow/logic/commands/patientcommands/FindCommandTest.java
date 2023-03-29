package seedu.careflow.logic.commands.patientcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;
import static seedu.careflow.testutil.TypicalPatients.CARL;
import static seedu.careflow.testutil.TypicalPatients.ELLE;
import static seedu.careflow.testutil.TypicalPatients.FIONA;
import static seedu.careflow.testutil.TypicalPatients.getTypicalPatientRecord;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.patient.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the CareFlowModel) for {@code FindCommand}.
 */
public class FindCommandTest {
    private CareFlowModel model = new CareFlowModelManager(getTypicalPatientRecord(), getTypicalDrugInventory(),
            new UserPrefs());
    private CareFlowModel expectedCareFlowModel = new CareFlowModelManager(getTypicalPatientRecord(),
            getTypicalDrugInventory(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedCareFlowModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedCareFlowModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedCareFlowModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedCareFlowModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

