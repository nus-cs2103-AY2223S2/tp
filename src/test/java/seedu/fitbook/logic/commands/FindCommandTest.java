package seedu.fitbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbook.testutil.TypicalClients.CARL;
import static seedu.fitbook.testutil.TypicalClients.ELLE;
import static seedu.fitbook.testutil.TypicalClients.FIONA;
import static seedu.fitbook.testutil.TypicalClients.getTypicalFitBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.client.FindContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the FitBookModel) for {@code FindCommand}.
 */
public class FindCommandTest {
    private FitBookModel model = new FitBookModelManager(getTypicalFitBook(), new UserPrefs());
    private FitBookModel expectedFitBookModel = new FitBookModelManager(getTypicalFitBook(), new UserPrefs());

    @Test
    public void equals() {
        FindContainsKeywordsPredicate firstPredicate =
                new FindContainsKeywordsPredicate(Collections.singletonList("first"));
        FindContainsKeywordsPredicate secondPredicate =
                new FindContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different client -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noClientFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedFitBookModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedFitBookModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedFitBookModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedFitBookModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FindContainsKeywordsPredicate preparePredicate(String userInput) {
        return new FindContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
