package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.BART;
import static seedu.address.testutil.TypicalPersons.CLARK;
import static seedu.address.testutil.TypicalPersons.EDWARD;
import static seedu.address.testutil.TypicalPersons.FORD;
import static seedu.address.testutil.TypicalPersons.ISAAC;
import static seedu.address.testutil.TypicalPersons.JUKUN;
import static seedu.address.testutil.TypicalPersons.KEVIN;
import static seedu.address.testutil.TypicalPersons.LAVENDER;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.Person;

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
        createEquals(firstKeyword, secondKeyword, Prefix.NAME);
        createEquals(firstKeyword, secondKeyword, Prefix.PHONE);
        createEquals(firstKeyword, secondKeyword, Prefix.EMAIL);
        createEquals(firstKeyword, secondKeyword, Prefix.TELEGRAM_HANDLE);
        createEquals(firstKeyword, secondKeyword, Prefix.ADDRESS);
        createEquals(firstKeyword, secondKeyword, Prefix.MODULE_TAG);
        createEquals(firstKeyword, secondKeyword, Prefix.GROUP_TAG);
    }

    public void createEquals(List<String> firstKeyword, List<String> secondKeyword, Prefix prefix) {
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
        createZeroKeywordsNoPersonFound(Prefix.NAME);
        createZeroKeywordsNoPersonFound(Prefix.ADDRESS);
        createZeroKeywordsNoPersonFound(Prefix.EMAIL);
        createZeroKeywordsNoPersonFound(Prefix.PHONE);
        createZeroKeywordsNoPersonFound(Prefix.GROUP_TAG);
        createZeroKeywordsNoPersonFound(Prefix.MODULE_TAG);
        createZeroKeywordsNoPersonFound(Prefix.TELEGRAM_HANDLE);
    }

    public void createZeroKeywordsNoPersonFound(Prefix prefix) {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ContainsKeywordsPredicate predicate = preparePredicate(" ", prefix);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        createMultipleKeywordsMultiplePersonsFound(
                Prefix.NAME,
                "Kee Edward Canning",
                Arrays.asList(CLARK, EDWARD, FORD),
                3
        );
        createMultipleKeywordsMultiplePersonsFound(
                Prefix.EMAIL,
                "albertpark bartlee clarkkee",
                Arrays.asList(ALBERT, BART, CLARK),
                3
        );
        createMultipleKeywordsMultiplePersonsFound(
                Prefix.ADDRESS,
                "Joo Koon",
                Collections.singletonList(JUKUN),
                1
        );
        createMultipleKeywordsMultiplePersonsFound(
                Prefix.PHONE,
                "92463693 88032666 83340546",
                Arrays.asList(EDWARD, FORD, LAVENDER),
                3
        );
        createMultipleKeywordsMultiplePersonsFound(
                Prefix.TELEGRAM_HANDLE,
                "@kevinho @jukun @isaacnewton",
                Arrays.asList(ISAAC, JUKUN, KEVIN),
                3
        );
    }

    public void createMultipleKeywordsMultiplePersonsFound(
            Prefix prefix, String userInput, List<Person> expectedOutput, int numOfPerson) {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, numOfPerson);
        ContainsKeywordsPredicate predicate = preparePredicate(userInput, prefix);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedOutput, model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code ContainsKeywordsPredicate}.
     */
    private ContainsKeywordsPredicate preparePredicate(String userInput, Prefix prefix) {
        return new ContainsKeywordsPredicate(
                Arrays.asList(userInput.split("\\s+")), prefix);
    }
}
