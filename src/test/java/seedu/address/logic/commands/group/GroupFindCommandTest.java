package seedu.address.logic.commands.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_GROUPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.CS2103_GROUP;
import static seedu.address.testutil.TypicalGroups.CS2103_MEMBER;
import static seedu.address.testutil.TypicalGroups.FRIENDS_GROUP;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithEmptyGroups;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithSingleMember;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.MemberOfGroupPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GroupFindCommand}.
 */
public class GroupFindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithEmptyGroups(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithEmptyGroups(), new UserPrefs());

    @Test
    public void equals() {
        List<String> firstList = Collections.singletonList("first");
        List<String> secondList = Collections.singletonList("second");
        GroupNameContainsKeywordsPredicate firstGroupPredicate =
                new GroupNameContainsKeywordsPredicate(firstList);
        MemberOfGroupPredicate firstMemberPredicate = new MemberOfGroupPredicate(firstList);
        GroupNameContainsKeywordsPredicate secondGroupPredicate =
                new GroupNameContainsKeywordsPredicate(secondList);
        MemberOfGroupPredicate secondMemberPredicate = new MemberOfGroupPredicate(secondList);

        GroupFindCommand findFirstCommand = new GroupFindCommand(firstGroupPredicate, firstMemberPredicate);
        GroupFindCommand findSecondCommand = new GroupFindCommand(secondGroupPredicate, secondMemberPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        GroupFindCommand findFirstCommandCopy = new GroupFindCommand(firstGroupPredicate, firstMemberPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different keyword -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noGroupFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 0);
        String whitespaceInput = " ";
        GroupNameContainsKeywordsPredicate groupPredicate = prepareGroupPredicate(whitespaceInput);
        MemberOfGroupPredicate memberPredicate = prepareMemberPredicate(whitespaceInput);
        GroupFindCommand command = new GroupFindCommand(groupPredicate, memberPredicate);
        expectedModel.updateFilteredGroupList(groupPredicate);
        expectedModel.updateFilteredPersonList(memberPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGroupList());
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleGroupsFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 2);
        String multipleKeywords = "CS2103 Friends";
        GroupNameContainsKeywordsPredicate groupPredicate = prepareGroupPredicate(multipleKeywords);
        MemberOfGroupPredicate memberPredicate = prepareMemberPredicate(multipleKeywords);
        GroupFindCommand command = new GroupFindCommand(groupPredicate, memberPredicate);
        expectedModel.updateFilteredGroupList(groupPredicate);
        expectedModel.updateFilteredPersonList(memberPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103_GROUP, FRIENDS_GROUP), model.getFilteredGroupList());
    }

    @Test
    public void execute_singleKeyword_singleMemberFound() {
        Model model = new ModelManager(getTypicalAddressBookWithSingleMember(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBookWithSingleMember(), new UserPrefs());

        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 1);
        String singleKeyword = "CS2103";
        GroupNameContainsKeywordsPredicate groupPredicate = prepareGroupPredicate(singleKeyword);
        MemberOfGroupPredicate memberPredicate = prepareMemberPredicate(singleKeyword);
        GroupFindCommand command = new GroupFindCommand(groupPredicate, memberPredicate);
        expectedModel.updateFilteredGroupList(groupPredicate);
        expectedModel.updateFilteredPersonList(memberPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(CS2103_GROUP), model.getFilteredGroupList());
        assertEquals(List.of(CS2103_MEMBER), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code GroupNameContainsKeywordsPredicate}.
     */
    private GroupNameContainsKeywordsPredicate prepareGroupPredicate(String userInput) {
        return new GroupNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code MemberOfGroupPredicate}.
     */
    private MemberOfGroupPredicate prepareMemberPredicate(String userInput) {
        return new MemberOfGroupPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
