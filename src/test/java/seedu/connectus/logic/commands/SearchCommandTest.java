package seedu.connectus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.connectus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.connectus.testutil.TypicalPersons.BENSON;
import static seedu.connectus.testutil.TypicalPersons.DANIEL;
import static seedu.connectus.testutil.TypicalPersons.ELLE;
import static seedu.connectus.testutil.TypicalPersons.getTypicalConnectUs;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.connectus.model.Model;
import seedu.connectus.model.ModelManager;
import seedu.connectus.model.UserPrefs;
import seedu.connectus.model.person.FieldsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {
    private Model model = new ModelManager(getTypicalConnectUs(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalConnectUs(), new UserPrefs());

    @Test
    public void equals() {
        FieldsContainKeywordsPredicate firstPredicate =
                new FieldsContainKeywordsPredicate(Collections.singletonList("first"));
        FieldsContainKeywordsPredicate secondPredicate =
                new FieldsContainKeywordsPredicate(Collections.singletonList("second"));

        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate);
        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstPredicate);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_wrongKeywords_noPersonFound() {
        //when keyword is not present in any contact
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FieldsContainKeywordsPredicate predicate = preparePredicate("xqdie");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FieldsContainKeywordsPredicate predicate = preparePredicate("Meier");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, ELLE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code FieldContainsKeywordsPredicate}.
     */
    private FieldsContainKeywordsPredicate preparePredicate(String userInput) {
        return new FieldsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
