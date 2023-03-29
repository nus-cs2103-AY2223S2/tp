package seedu.roles.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.roles.commons.core.Messages.MESSAGE_ROLES_LISTED_OVERVIEW;
import static seedu.roles.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.roles.testutil.TypicalRoles.CARL;
import static seedu.roles.testutil.TypicalRoles.ELLE;
import static seedu.roles.testutil.TypicalRoles.FIONA;
import static seedu.roles.testutil.TypicalRoles.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;
import seedu.roles.model.UserPrefs;
import seedu.roles.model.job.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code NameCommand}.
 */
public class NameCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        NameCommand findFirstCommand = new NameCommand(firstPredicate);
        NameCommand findSecondCommand = new NameCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        NameCommand findFirstCommandCopy = new NameCommand(firstPredicate);
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
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        NameCommand command = new NameCommand(predicate);
        expectedModel.updateFilteredRoleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRoleList());
    }

    @Test
    public void execute_multipleKeywords_multipleRolesFound() {
        String expectedMessage = String.format(MESSAGE_ROLES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        NameCommand command = new NameCommand(predicate);
        expectedModel.updateFilteredRoleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredRoleList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
