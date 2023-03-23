package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.ALEX;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.DANIEL;
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
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.NricContainsKeywordsPredicate;
import seedu.address.model.patient.StatusContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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

        // different patient -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Carl Elle Fiona");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByNric_onePatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        NricContainsKeywordsPredicate predicate = prepareNricPredicate("S1235567A");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByNric_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 2);
        NricContainsKeywordsPredicate predicate = prepareNricPredicate("S1235567A S6969696B");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA), model.getFilteredPatientList());
    }

    @Test
    public void execute_findByStatus_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 7);
        StatusContainsKeywordsPredicate predicate = prepareStatusPredicate("GRAY");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALEX, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPatientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private NricContainsKeywordsPredicate prepareNricPredicate(String userInput) {
        return new NricContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private StatusContainsKeywordsPredicate prepareStatusPredicate(String userInput) {
        return new StatusContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
