package seedu.patientist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.patientist.testutil.TypicalStaff.CHARLES;
import static seedu.patientist.testutil.TypicalStaff.DACIA;
import static seedu.patientist.testutil.TypicalWards.getTypicalPatientist;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.Model;
import seedu.patientist.model.ModelManager;
import seedu.patientist.model.UserPrefs;
import seedu.patientist.model.person.staff.StaffNameContainsKeywordsPredicate;

public class FindStaffCommandTest {
    private Model model = new ModelManager(getTypicalPatientist(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());

    @Test
    public void equals() {
        StaffNameContainsKeywordsPredicate firstPredicate =
                new StaffNameContainsKeywordsPredicate(Collections.singletonList("first"));
        StaffNameContainsKeywordsPredicate secondPredicate =
                new StaffNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindStaffCommand findFirstCommand = new FindStaffCommand(firstPredicate);
        FindStaffCommand findSecondCommand = new FindStaffCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStaffCommand findFirstCommandCopy = new FindStaffCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        StaffNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindStaffCommand command = new FindStaffCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        StaffNameContainsKeywordsPredicate predicate = preparePredicate("Dacia Charles");
        FindStaffCommand command = new FindStaffCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHARLES, DACIA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private StaffNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StaffNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
