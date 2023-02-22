package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CLARK;
import static seedu.address.testutil.TypicalPersons.EDWARD;
import static seedu.address.testutil.TypicalPersons.FORD;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalEduMate(), new UserPrefs());

    @Test
    public void equals() {
        List<String> firstKeyword = Collections.singletonList("first");
        List<String> secondKeyword = Collections.singletonList("second");
        create_equals(firstKeyword, secondKeyword, CliSyntax.PREFIX_NAME);
        create_equals(firstKeyword, secondKeyword, CliSyntax.PREFIX_PHONE);
        create_equals(firstKeyword, secondKeyword, CliSyntax.PREFIX_EMAIL);
        create_equals(firstKeyword, secondKeyword, CliSyntax.PREFIX_TELEGRAM_HANDLE);
        create_equals(firstKeyword, secondKeyword, CliSyntax.PREFIX_ADDRESS);
        create_equals(firstKeyword, secondKeyword, CliSyntax.PREFIX_MODULE_TAG);
        create_equals(firstKeyword, secondKeyword, CliSyntax.PREFIX_GROUP_TAG);
    }

    public void create_equals(List<String> firstKeyword, List<String> secondKeyword, Prefix prefix) {
        ContainsKeywordsPredicate firstPredicate =
                new ContainsKeywordsPredicate(firstKeyword, prefix);
        ContainsKeywordsPredicate secondPredicate =
                new ContainsKeywordsPredicate(secondKeyword, prefix);

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        create_zeroKeywords_noPersonFound(CliSyntax.PREFIX_NAME);
        create_zeroKeywords_noPersonFound(CliSyntax.PREFIX_ADDRESS);
        create_zeroKeywords_noPersonFound(CliSyntax.PREFIX_EMAIL);
        create_zeroKeywords_noPersonFound(CliSyntax.PREFIX_PHONE);
        create_zeroKeywords_noPersonFound(CliSyntax.PREFIX_GROUP_TAG);
        create_zeroKeywords_noPersonFound(CliSyntax.PREFIX_MODULE_TAG);
        create_zeroKeywords_noPersonFound(CliSyntax.PREFIX_TELEGRAM_HANDLE);
    }

    public void create_zeroKeywords_noPersonFound(Prefix prefix) {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ContainsKeywordsPredicate predicate = preparePredicate(" ", prefix);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ContainsKeywordsPredicate predicate = preparePredicate("Kee Edward Canning", CliSyntax.PREFIX_NAME);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CLARK, EDWARD, FORD), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code ContainsKeywordsPredicate}.
     */
    private ContainsKeywordsPredicate preparePredicate(String userInput, Prefix prefix) {
        return new ContainsKeywordsPredicate(
                Arrays.asList(userInput.split("\\s+")), prefix);
    }
}
