package codoc.logic.commands;

import static codoc.testutil.TypicalPersons.getTypicalCodoc;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import codoc.model.Model;
import codoc.model.ModelManager;
import codoc.model.UserPrefs;
import codoc.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalCodoc(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCodoc(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate, firstPredicate.toString());
        FindCommand findSecondCommand = new FindCommand(secondPredicate, secondPredicate.toString());

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, firstPredicate.toString());
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    //    @Test // Yet to fix
    //    public void execute_zeroKeywords_noPersonFound() {
    //        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
    //        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
    //        FindCommand command = new FindCommand(predicate);
    //        expectedModel.updateFilteredPersonList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    //    }

    //    @Test // Yet to fix
    //    public void execute_multipleKeywords_multiplePersonsFound() {
    //        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    //        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
    //        FindCommand command = new FindCommand(predicate);
    //        expectedModel.updateFilteredPersonList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    //    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
