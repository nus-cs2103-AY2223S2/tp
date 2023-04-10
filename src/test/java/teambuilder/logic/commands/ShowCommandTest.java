package teambuilder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static teambuilder.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static teambuilder.logic.commands.CommandTestUtil.assertCommandSuccess;
import static teambuilder.testutil.TypicalPersons.BENSON;
import static teambuilder.testutil.TypicalPersons.CARL;
import static teambuilder.testutil.TypicalPersons.DANIEL;
import static teambuilder.testutil.TypicalPersons.ELLE;
import static teambuilder.testutil.TypicalPersons.FIONA;
import static teambuilder.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import teambuilder.model.Model;
import teambuilder.model.ModelManager;
import teambuilder.model.UserPrefs;
import teambuilder.model.person.TeamContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ShowCommand}.
 */
class ShowCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TeamContainsKeywordsPredicate firstPredicate =
                new TeamContainsKeywordsPredicate(Collections.singletonList("first"));
        TeamContainsKeywordsPredicate secondPredicate =
                new TeamContainsKeywordsPredicate(Collections.singletonList("second"));

        ShowCommand showFirstCommand = new ShowCommand(firstPredicate);
        ShowCommand showSecondCommand = new ShowCommand(secondPredicate);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(firstPredicate);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TeamContainsKeywordsPredicate predicate = preparePredicate(" ");
        ShowCommand command = new ShowCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        TeamContainsKeywordsPredicate predicate = preparePredicate("TeamA TeamB TeamC");
        ShowCommand command = new ShowCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, DANIEL, ELLE, FIONA), model.getSortedPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code TeamContainsKeywordsPredicate}.
     */
    private TeamContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TeamContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
