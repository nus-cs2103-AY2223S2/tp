package seedu.roles.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.roles.commons.core.Messages.MESSAGE_ROLES_LISTED_OVERVIEW;
import static seedu.roles.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.roles.testutil.TypicalRoles.CARL;
import static seedu.roles.testutil.TypicalRoles.ELLE;
import static seedu.roles.testutil.TypicalRoles.FIONA;
import static seedu.roles.testutil.TypicalRoles.getTypicalRoleBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;
import seedu.roles.model.UserPrefs;
import seedu.roles.model.job.CompanyContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code CompanyCommand}.
 */
public class CompanyCommandTest {
    private Model model = new ModelManager(getTypicalRoleBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRoleBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyContainsKeywordsPredicate firstPredicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("first"));
        CompanyContainsKeywordsPredicate secondPredicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("second"));

        CompanyCommand findFirstCommand = new CompanyCommand(firstPredicate);
        CompanyCommand findSecondCommand = new CompanyCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        CompanyCommand findFirstCommandCopy = new CompanyCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different role -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noRoleFound() {
        String expectedMessage = String.format(MESSAGE_ROLES_LISTED_OVERVIEW, 0);
        CompanyContainsKeywordsPredicate predicate = preparePredicate(" ");
        CompanyCommand command = new CompanyCommand(predicate);
        expectedModel.updateFilteredRoleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRoleList());
    }

    @Test
    public void execute_multipleKeywords_multipleRolesFound() {
        String expectedMessage = String.format(MESSAGE_ROLES_LISTED_OVERVIEW, 3);
        CompanyContainsKeywordsPredicate predicate = preparePredicate("NUS");
        CompanyCommand command = new CompanyCommand(predicate);
        expectedModel.updateFilteredRoleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredRoleList());
    }

    /**
     * Parses {@code userInput} into a {@code CompanyContainsKeywordsPredicate}.
     */
    private CompanyContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CompanyContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
