package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_LISTINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalListings.CHICKEN_RICE_UNCLE;
import static seedu.address.testutil.TypicalListings.getTypicalListingBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.listing.TitleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalListingBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalListingBook(), new UserPrefs());

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("first"));
        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 0);
        TitleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredListingList());
    }

    @Test
    public void execute_singleKeyword_singleListingFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 1);
        TitleContainsKeywordsPredicate predicate = preparePredicate("chicken");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHICKEN_RICE_UNCLE), model.getFilteredListingList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TitleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
