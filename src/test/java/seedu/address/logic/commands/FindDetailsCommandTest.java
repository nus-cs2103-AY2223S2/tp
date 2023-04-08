package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.ELLE;
import static seedu.address.testutil.TypicalPatients.FIONA;
import static seedu.address.testutil.TypicalPatients.GEORGE;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.DetailsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindDetailsCommand}.
 */
public class FindDetailsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentList(), new UserPrefs());
    private Model expectedModel =
        new ModelManager(getTypicalAddressBook(), getTypicalAppointmentList(), new UserPrefs());

    @Test
    public void equals() {
        DetailsContainKeywordsPredicate firstPredicate =
            new DetailsContainKeywordsPredicate(Collections.singletonList("first"));
        DetailsContainKeywordsPredicate secondPredicate =
            new DetailsContainKeywordsPredicate(Collections.singletonList("second"));

        FindDetailsCommand findFirstCommand = new FindDetailsCommand(firstPredicate);
        FindDetailsCommand findSecondCommand = new FindDetailsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDetailsCommand findFirstCommandCopy = new FindDetailsCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        DetailsContainKeywordsPredicate predicate = preparePredicate(" ");
        FindDetailsCommand command = new FindDetailsCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 4);
        DetailsContainKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz 4th");
        FindDetailsCommand command = new FindDetailsCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA, GEORGE), model.getFilteredPatientList());
    }

    /**
     * Parses {@code userInput} into a {@code DetailsContainKeywordsPredicate}.
     */
    private DetailsContainKeywordsPredicate preparePredicate(String userInput) {
        return new DetailsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
