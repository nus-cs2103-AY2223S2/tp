package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOpenings.AMAZON;
import static seedu.address.testutil.TypicalOpenings.GRAB;
import static seedu.address.testutil.TypicalOpenings.META;
import static seedu.address.testutil.TypicalOpenings.getTypicalUltron;
import static seedu.ultron.commons.core.Messages.MESSAGE_OPENING_LISTED_OVERVIEW;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.ultron.logic.commands.FindCommand;
import seedu.ultron.model.Model;
import seedu.ultron.model.ModelManager;
import seedu.ultron.model.UserPrefs;
import seedu.ultron.model.opening.CompanyOrPositionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalUltron(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalUltron(), new UserPrefs());

    @Test
    public void equals() {
        CompanyOrPositionContainsKeywordsPredicate firstPredicate =
                new CompanyOrPositionContainsKeywordsPredicate(Collections.singletonList("first"));
        CompanyOrPositionContainsKeywordsPredicate secondPredicate =
                new CompanyOrPositionContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different opening -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noOpeningFound() {
        String expectedMessage = String.format(MESSAGE_OPENING_LISTED_OVERVIEW, 0);
        CompanyOrPositionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredOpeningList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOpeningList());
    }

    @Test
    public void execute_multipleKeywords_multipleOpeningsFound() {
        String expectedMessage = String.format(MESSAGE_OPENING_LISTED_OVERVIEW, 3);
        CompanyOrPositionContainsKeywordsPredicate predicate = preparePredicate("gRaB amAzOn meta");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredOpeningList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new HashSet(Arrays.asList(GRAB, META, AMAZON)), new HashSet(model.getFilteredOpeningList()));
    }

    /**
     * Parses {@code userInput} into a {@code CompanyOrPositionContainsKeywordsPredicate}.
     */
    private CompanyOrPositionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CompanyOrPositionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
