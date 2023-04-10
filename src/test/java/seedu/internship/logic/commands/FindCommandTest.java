package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.commons.core.Messages.MESSAGE_INTERNSHIP_LISTED_OVERVIEW;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.testutil.TypicalInternships.GOLDMAN;
import static seedu.internship.testutil.TypicalInternships.RIOTGAMES;
import static seedu.internship.testutil.TypicalInternships.SAMSUNG;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternBuddy;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalInternBuddy(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternBuddy(), new UserPrefs());

    @Test
    public void equals() {
        InternshipContainsKeywordsPredicate firstPredicate =
                new InternshipContainsKeywordsPredicate(Collections.singletonList("first"),
                        Collections.singletonList("first"), Collections.singletonList("first"),
                        Collections.singletonList("first"), Collections.singletonList("first"));
        InternshipContainsKeywordsPredicate secondPredicate =
                new InternshipContainsKeywordsPredicate(Collections.singletonList("second"),
                        Collections.singletonList("second"), Collections.singletonList("second"),
                        Collections.singletonList("second"), Collections.singletonList("second"));

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

        // different internship -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noKeywords_noInternshipFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIP_LISTED_OVERVIEW, 0);
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate(Collections.emptyList(), Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInternshipList());
    }

    @Test
    public void execute_multipleKeywords_multipleInternshipsFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIP_LISTED_OVERVIEW, 3);
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate(Arrays.asList("Goldman", "Riot Games", "Samsung"),
                        Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                        Collections.emptyList());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GOLDMAN, RIOTGAMES, SAMSUNG), model.getFilteredInternshipList());
    }
}
