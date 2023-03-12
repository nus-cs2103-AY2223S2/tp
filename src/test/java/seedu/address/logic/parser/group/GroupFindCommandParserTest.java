package seedu.address.logic.parser.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_GROUPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.CS2103_GROUP;
import static seedu.address.testutil.TypicalGroups.FRIENDS_GROUP;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithEmptyGroups;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.group.GroupFindCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;

class GroupFindCommandParserTest {
    private Model model = new ModelManager(getTypicalAddressBookWithEmptyGroups(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithEmptyGroups(), new UserPrefs());

    @Test
    public void equals() {
        GroupNameContainsKeywordsPredicate firstPredicate =
                new GroupNameContainsKeywordsPredicate(Collections.singletonList("first"));
        GroupNameContainsKeywordsPredicate secondPredicate =
                new GroupNameContainsKeywordsPredicate(Collections.singletonList("second"));

        GroupFindCommand findFirstCommand = new GroupFindCommand(firstPredicate);
        GroupFindCommand findSecondCommand = new GroupFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        GroupFindCommand findFirstCommandCopy = new GroupFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different keyword -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noGroupFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 0);
        GroupNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        GroupFindCommand command = new GroupFindCommand(predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGroupList());
    }

    @Test
    public void execute_multipleKeywords_multipleGroupsFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 2);
        GroupNameContainsKeywordsPredicate predicate = preparePredicate("Friends CS2103");
        GroupFindCommand command = new GroupFindCommand(predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FRIENDS_GROUP, CS2103_GROUP), model.getFilteredGroupList());
    }

    /**
     * Parses {@code userInput} into a {@code GroupNameContainsKeywordsPredicate}.
     */
    private GroupNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new GroupNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
