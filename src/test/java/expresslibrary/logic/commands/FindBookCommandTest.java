package expresslibrary.logic.commands;

import static expresslibrary.commons.core.Messages.MESSAGE_BOOK_FOUND_OVERVIEW;
import static expresslibrary.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static expresslibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalBooks.DUNE;
import static expresslibrary.testutil.TypicalBooks.EMMA;
import static expresslibrary.testutil.TypicalBooks.GREAT_GATSBY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.book.BookNameContainsKeywordsPredicate;

class FindBookCommandTest {
    private Model model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());

    @Test
    public void equals() {
        BookNameContainsKeywordsPredicate firstPredicate = new BookNameContainsKeywordsPredicate(
                Collections.singletonList("first"));
        BookNameContainsKeywordsPredicate secondPredicate = new BookNameContainsKeywordsPredicate(
                Collections.singletonList("second"));

        FindBookCommand findFirstCommand = new FindBookCommand(firstPredicate);
        FindBookCommand findSecondCommand = new FindBookCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBookCommand findFirstCommandCopy = new FindBookCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOK_FOUND_OVERVIEW, 0);
        BookNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_multipleKeywords_multipleBooksFound() {
        String expectedMessage = String.format(MESSAGE_BOOK_FOUND_OVERVIEW, 3);
        BookNameContainsKeywordsPredicate predicate = preparePredicate("Dune Emma Great");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DUNE, EMMA, GREAT_GATSBY), model.getFilteredBookList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private BookNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new BookNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}