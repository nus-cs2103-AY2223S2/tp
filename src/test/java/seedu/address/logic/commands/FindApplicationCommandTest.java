package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.NameContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICATIONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.*;

/**
 * Contains integration tests (interaction with the Application Model) for {@code FindApplicationCommand}.
 */
public class FindApplicationCommandTest {
    private ApplicationModel model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());
    private ApplicationModel expectedModel = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindApplicationCommand findFirstCommand = new FindApplicationCommand(firstPredicate);
        FindApplicationCommand findSecondCommand = new FindApplicationCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindApplicationCommand findFirstCommandCopy = new FindApplicationCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicationFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATIONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindApplicationCommand command = new FindApplicationCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicationList());
    }

    @Test
    public void execute_multipleKeywords_multipleApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATIONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Google Amazon Apple");
        FindApplicationCommand command = new FindApplicationCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GOOGLE, AMAZON, APPLE), model.getFilteredApplicationList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
