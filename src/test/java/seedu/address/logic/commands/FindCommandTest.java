package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INTERNSHIP_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.GOLDMAN;
import static seedu.address.testutil.TypicalInternships.RIOTGAMES;
import static seedu.address.testutil.TypicalInternships.SAMSUNG;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        InternshipContainsKeywordsPredicate firstPredicate =
                new InternshipContainsKeywordsPredicate(Collections.singletonList("first"),
                        Collections.singletonList("first"), Collections.singletonList("first"),
                        Collections.singletonList("first"));
        InternshipContainsKeywordsPredicate secondPredicate =
                new InternshipContainsKeywordsPredicate(Collections.singletonList("second"),
                        Collections.singletonList("second"), Collections.singletonList("second"),
                        Collections.singletonList("second"));

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
                        Collections.emptyList(), Collections.emptyList());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInternshipList());
    }

    @Test
    public void execute_multipleKeywords_multipleInternshipsFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIP_LISTED_OVERVIEW, 3);
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate(Arrays.asList("Goldman", "Riot", "Samsung"),
                        Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GOLDMAN, RIOTGAMES, SAMSUNG), model.getFilteredInternshipList());
    }
}
