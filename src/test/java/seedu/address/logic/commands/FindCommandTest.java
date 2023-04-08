package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.PredicateKey;
import seedu.address.model.user.UserData;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.NAME, Collections.singletonList("first"));
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.NAME, Collections.singletonList("second"));


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

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsKeywordsPredicate predicate = preparePredicate("");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PersonContainsKeywordsPredicate().withField(PredicateKey.NAME,
                Arrays.asList(userInput.split("\\s+")));
    }

    //TODO: Test if Find command works for different fields.
    @Test
    public void execute_singleAddressKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate addressPredicate =
                new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.ADDRESS, Collections.singletonList("street"));
        FindCommand command = new FindCommand(addressPredicate);
        expectedModel.updateFilteredPersonList(addressPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleAddressKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonContainsKeywordsPredicate addressPredicate =
                new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.ADDRESS, Arrays.asList("wall ave".split("\\s+")));
        FindCommand command = new FindCommand(addressPredicate);
        expectedModel.updateFilteredPersonList(addressPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, ELLE), model.getFilteredPersonList());
    }
    @Test
    public void execute_numberAddressKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonContainsKeywordsPredicate addressPredicate =
                new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.ADDRESS, Arrays.asList("1 4".split("\\s+")));
        FindCommand command = new FindCommand(addressPredicate);
        expectedModel.updateFilteredPersonList(addressPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, GEORGE), model.getFilteredPersonList());
    }
    @Test
    public void execute_commKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonContainsKeywordsPredicate commsPredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.COMMS,
                        Arrays.asList("telegram".split("\\s+")));
        FindCommand command = new FindCommand(commsPredicate);
        expectedModel.updateFilteredPersonList(commsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
    @Test
    public void execute_singleLetterKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        PersonContainsKeywordsPredicate commsPredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.COMMS,
                        Arrays.asList("a".split("\\s+")));
        FindCommand command = new FindCommand(commsPredicate);
        expectedModel.updateFilteredPersonList(commsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }
    @Test
    public void execute_emailKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonContainsKeywordsPredicate emailPredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.EMAIL,
                        Arrays.asList("example".split("\\s+")));
        FindCommand command = new FindCommand(emailPredicate);
        expectedModel.updateFilteredPersonList(emailPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredPersonList());
    }
    @Test
    public void execute_facultyKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonContainsKeywordsPredicate facultyPredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.FACULTY,
                        Arrays.asList("SOC".split("\\s+")));
        FindCommand command = new FindCommand(facultyPredicate);
        expectedModel.updateFilteredPersonList(facultyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL),
                model.getFilteredPersonList());
    }
    @Test
    public void execute_genderKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate genderPredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.GENDER,
                        Arrays.asList("female undefined".split("\\s+")));
        FindCommand command = new FindCommand(genderPredicate);
        expectedModel.updateFilteredPersonList(genderPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA),
                model.getFilteredPersonList());
    }
    @Test
    public void execute_majorKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        PersonContainsKeywordsPredicate majorPredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.MAJOR,
                        Arrays.asList("comp psy".split("\\s+")));
        FindCommand command = new FindCommand(majorPredicate);
        expectedModel.updateFilteredPersonList(majorPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE, FIONA, GEORGE),
                model.getFilteredPersonList());
    }
    @Test
    public void execute_raceKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        PersonContainsKeywordsPredicate racePredicate =
                new PersonContainsKeywordsPredicate().withField(PredicateKey.RACE,
                        Arrays.asList("chinese indian".split("\\s+")));
        FindCommand command = new FindCommand(racePredicate);
        expectedModel.updateFilteredPersonList(racePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE, FIONA, GEORGE),
                model.getFilteredPersonList());
    }
    @Test
    public void execute_noSplit_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonContainsKeywordsPredicate modulesPredicate =
                new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.RACE, Arrays.asList("chinese indian"));
        FindCommand command = new FindCommand(modulesPredicate);
        expectedModel.updateFilteredPersonList(modulesPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE),
                model.getFilteredPersonList());
    }
    @Test
    public void execute_multipleRaceFields_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        PersonContainsKeywordsPredicate racePredicate =
                new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.RACE, Arrays.asList("chinese ".split("\\s+")))
                        .withField(PredicateKey.RACE, Arrays.asList("  indian".split("\\s+")));
        FindCommand command = new FindCommand(racePredicate);
        expectedModel.updateFilteredPersonList(racePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE, FIONA, GEORGE),
                model.getFilteredPersonList());
    }
    @Test
    public void execute_modulesFields_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate modulesPredicate =
                new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.MODULES, Arrays.asList("cs2103".split("\\s+")));
        FindCommand command = new FindCommand(modulesPredicate);
        expectedModel.updateFilteredPersonList(modulesPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, DANIEL),
                model.getFilteredPersonList());
    }
}
