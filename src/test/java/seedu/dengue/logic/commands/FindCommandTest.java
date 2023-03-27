package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.CARL;
import static seedu.dengue.testutil.TypicalPersons.ELLE;
import static seedu.dengue.testutil.TypicalPersons.FIONA;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.predicate.FindPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

    @Test
    public void equals() {
        FindPredicate firstPredicate =
                new FindPredicate(Collections.singletonList("first"));
        FindPredicate secondPredicate =
                new FindPredicate(Collections.singletonList("second"));

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
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FindPredicate preparePredicate(String userInput) {
        return new FindPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
