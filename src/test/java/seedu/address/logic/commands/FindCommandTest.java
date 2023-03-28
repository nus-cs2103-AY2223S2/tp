package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_LISTINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalListings.CHICKEN_RICE_UNCLE;
import static seedu.address.testutil.TypicalListings.GAME_DEVELOPER;
import static seedu.address.testutil.TypicalListings.MEDIA_DEVELOPER;
import static seedu.address.testutil.TypicalListings.SOFTWARE_DEVELOPER;
import static seedu.address.testutil.TypicalListings.TOILET_CLEANER;
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
    public void execute_zeroKeywords_noListingFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 0);
        TitleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingBook(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getDisplayedListingBook());
    }

    @Test
    public void execute_singleKeyword_noListingFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 0);
        TitleContainsKeywordsPredicate predicate = preparePredicate("CEO");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingBook(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getDisplayedListingBook());
    }

    @Test
    public void execute_singleKeyword_singleListingFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 1);
        TitleContainsKeywordsPredicate predicate = preparePredicate("Chicken");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingBook(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHICKEN_RICE_UNCLE), model.getDisplayedListingBook());
    }

    @Test
    public void execute_singleKeyword_multipleListingsFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 3);
        TitleContainsKeywordsPredicate predicate = preparePredicate("Developer");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingBook(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEDIA_DEVELOPER, GAME_DEVELOPER,
                SOFTWARE_DEVELOPER), model.getDisplayedListingBook());
    }

    @Test
    public void execute_multipleKeywords_noListingFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 0);
        TitleContainsKeywordsPredicate predicate = preparePredicate("CEO Mechanic");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingBook(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getDisplayedListingBook());
    }

    @Test
    public void execute_multipleKeywords_singleListingFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 1);
        TitleContainsKeywordsPredicate predicate = preparePredicate("CEO Chicken");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingBook(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHICKEN_RICE_UNCLE), model.getDisplayedListingBook());
    }

    @Test
    public void execute_multipleKeywords_multipleListingsFound() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 2);
        TitleContainsKeywordsPredicate predicate = preparePredicate("Chicken Toilet");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingBook(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHICKEN_RICE_UNCLE, TOILET_CLEANER), model.getDisplayedListingBook());
    }

    @Test
    public void keyword_isCaseInsensitive() {
        String expectedMessage = String.format(MESSAGE_LISTINGS_LISTED_OVERVIEW, 1);
        TitleContainsKeywordsPredicate predicate = preparePredicate("cHiCkeN riCE uNcLe");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredListingBook(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHICKEN_RICE_UNCLE), model.getDisplayedListingBook());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TitleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
