package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MedicineContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("James"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Bob"));

        NricContainsKeywordsPredicate firstNricPredicate =
                new NricContainsKeywordsPredicate(Collections.singletonList("T0012135T"));
        NricContainsKeywordsPredicate secondNricPredicate =
                new NricContainsKeywordsPredicate(Collections.singletonList("S0012135T"));

        TagContainsKeywordsPredicate firstTagPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("Asthmatic"));
        TagContainsKeywordsPredicate secondTagPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("Diabetic"));


        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        FindCommand findNricFirstCommand = new FindCommand(firstNricPredicate);
        FindCommand findNricSecondCommand = new FindCommand(secondNricPredicate);

        FindCommand findTagFirstCommand = new FindCommand(firstTagPredicate);
        FindCommand findTagSecondCommand = new FindCommand(secondTagPredicate);


        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findNricFirstCommand.equals(findNricFirstCommand));
        assertTrue(findTagFirstCommand.equals(findTagFirstCommand));


        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        FindCommand findNricFirstCommandCopy = new FindCommand(firstNricPredicate);
        FindCommand findTagFirstCommandCopy = new FindCommand(firstTagPredicate);


        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        assertTrue(findNricFirstCommand.equals(findNricFirstCommandCopy));
        assertTrue(findTagFirstCommand.equals(findTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findNricFirstCommand.equals(1));
        assertFalse(findTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findNricFirstCommand.equals(null));
        assertFalse(findTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
        assertFalse(findNricFirstCommand.equals(findNricSecondCommand));
        assertFalse(findTagFirstCommand.equals(findTagSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());


        MedicineContainsKeywordsPredicate predicate2 = prepareAddressPredicate(" ");
        FindCommand command2 = new FindCommand(predicate2);
        expectedModel.updateFilteredPersonList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        NricContainsKeywordsPredicate predicate3 = prepareNricPredicate(" ");
        FindCommand command3 = new FindCommand(predicate3);
        expectedModel.updateFilteredPersonList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        TagContainsKeywordsPredicate predicate4 = prepareTagPredicate(" ");
        FindCommand command4 = new FindCommand(predicate4);
        expectedModel.updateFilteredPersonList(predicate4);
        assertCommandSuccess(command4, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage1 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate1 = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate1);
        expectedModel.updateFilteredPersonList(predicate1);
        assertCommandSuccess(command, model, expectedMessage1, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());

        String expectedMessage2 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        MedicineContainsKeywordsPredicate predicate2 = prepareAddressPredicate("Aspirin");
        FindCommand command2 = new FindCommand(predicate2);
        expectedModel.updateFilteredPersonList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredPersonList());

        String expectedMessage3 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate3 = prepareTagPredicate("Epileptic");
        FindCommand command3 = new FindCommand(predicate3);
        expectedModel.updateFilteredPersonList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getFilteredPersonList());

        String expectedMessage4 = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NricContainsKeywordsPredicate predicate4 = prepareNricPredicate("S0123456D");
        FindCommand command4 = new FindCommand(predicate4);
        expectedModel.updateFilteredPersonList(predicate4);
        assertCommandSuccess(command4, model, expectedMessage4, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getFilteredPersonList());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code AddressContainsKeywordsPredicate}.
     */
    private MedicineContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new MedicineContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NricContainsKeywordsPredicate}.
     */
    private NricContainsKeywordsPredicate prepareNricPredicate(String userInput) {
        return new NricContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
