package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FindLeadStatusCommand.MESSAGE_INVALID_LEAD_STATUS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FindContainsStatusPredicate;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindLeadStatusCommand}.
 */
public class FindLeadStatusCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindContainsStatusPredicate firstPredicate = new FindContainsStatusPredicate(
                Collections.singletonList("first"));
        FindContainsStatusPredicate secondPredicate = new FindContainsStatusPredicate(
                Collections.singletonList("second"));

        FindLeadStatusCommand findFirstCommand = new FindLeadStatusCommand(firstPredicate);
        FindLeadStatusCommand findSecondCommand = new FindLeadStatusCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindLeadStatusCommand findFirstCommandCopy = new FindLeadStatusCommand(firstPredicate);
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
        String expectedMessage = String.format(MESSAGE_INVALID_LEAD_STATUS);
        FindContainsStatusPredicate predicate = preparePredicate(" ");
        FindLeadStatusCommand command = new FindLeadStatusCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    // Implement another time after talk to JJ
    // @Test
    // public void execute_multipleKeywords_multiplePersonsFound() {
    // String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    // FindContainsStatusPredicate predicate = preparePredicate("Uncontacted");
    // FindLeadStatusCommand command = new FindLeadStatusCommand(predicate);
    // expectedModel.updateFilteredPersonList(predicate);
    // assertCommandSuccess(command, model, expectedMessage, expectedModel);
    // assertEquals(Arrays.asList(CARL, ELLE, FIONA),
    // model.getFilteredPersonList());
    // }

    /**
     * Parses {@code userInput} into a {@code FindContainsStatusPredicate}.
     */
    private FindContainsStatusPredicate preparePredicate(String userInput) {
        return new FindContainsStatusPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
