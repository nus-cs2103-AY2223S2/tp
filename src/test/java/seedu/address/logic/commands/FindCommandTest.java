package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Phone;
import seedu.address.model.person.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicate.LanguageContainsKeywordsPredicate;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicate.PersonContainsKeywordsPredicate;
import seedu.address.model.person.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicate.ProfileContainsKeywordsPredicate;
import seedu.address.model.person.predicate.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void testEquals() {
        List<String> firstSingletonList = Collections.singletonList("first");
        List<String> secondSingletonList = Collections.singletonList("second");
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(firstSingletonList);
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(secondSingletonList);
        ProfileContainsKeywordsPredicate firstProfilePredicate =
                new ProfileContainsKeywordsPredicate(firstSingletonList);
        ProfileContainsKeywordsPredicate secondProfilePredicate =
                new ProfileContainsKeywordsPredicate(secondSingletonList);
        PhoneContainsKeywordsPredicate firstPhonePredicate =
                new PhoneContainsKeywordsPredicate(firstSingletonList);
        PhoneContainsKeywordsPredicate secondPhonePredicate =
                new PhoneContainsKeywordsPredicate(secondSingletonList);
        EmailContainsKeywordsPredicate firstEmailPredicate =
                new EmailContainsKeywordsPredicate(firstSingletonList);
        EmailContainsKeywordsPredicate secondEmailPredicate =
                new EmailContainsKeywordsPredicate(secondSingletonList);
        AddressContainsKeywordsPredicate firstAddressPredicate =
                new AddressContainsKeywordsPredicate((firstSingletonList));
        AddressContainsKeywordsPredicate secondAddressPredicate =
                new AddressContainsKeywordsPredicate(secondSingletonList);
        LanguageContainsKeywordsPredicate firstLanguagePredicate =
                new LanguageContainsKeywordsPredicate(firstSingletonList);
        LanguageContainsKeywordsPredicate secondLanguagePredicate =
                new LanguageContainsKeywordsPredicate(secondSingletonList);
        TagContainsKeywordsPredicate firstTagPredicate =
                new TagContainsKeywordsPredicate(firstSingletonList);
        TagContainsKeywordsPredicate secondTagPredicate =
                new TagContainsKeywordsPredicate(secondSingletonList);
        PersonContainsKeywordsPredicate firstPersonPredicate =
                new PersonContainsKeywordsPredicate(
                        firstNamePredicate,
                        firstProfilePredicate,
                        firstPhonePredicate,
                        firstEmailPredicate,
                        firstAddressPredicate,
                        firstLanguagePredicate,
                        firstTagPredicate);
        PersonContainsKeywordsPredicate secondNamePersonPredicate = new PersonContainsKeywordsPredicate(
                secondNamePredicate,
                firstProfilePredicate,
                firstPhonePredicate,
                firstEmailPredicate,
                firstAddressPredicate,
                firstLanguagePredicate,
                firstTagPredicate);
        PersonContainsKeywordsPredicate secondProfilePersonPredicate = new PersonContainsKeywordsPredicate(
                firstNamePredicate,
                secondProfilePredicate,
                firstPhonePredicate,
                firstEmailPredicate,
                firstAddressPredicate,
                firstLanguagePredicate,
                firstTagPredicate);
        PersonContainsKeywordsPredicate secondPhonePersonPredicate = new PersonContainsKeywordsPredicate(
                firstNamePredicate,
                firstProfilePredicate,
                secondPhonePredicate,
                firstEmailPredicate,
                firstAddressPredicate,
                firstLanguagePredicate,
                firstTagPredicate);
        PersonContainsKeywordsPredicate secondEmailPersonPredicate = new PersonContainsKeywordsPredicate(
                firstNamePredicate,
                firstProfilePredicate,
                firstPhonePredicate,
                secondEmailPredicate,
                firstAddressPredicate,
                firstLanguagePredicate,
                firstTagPredicate);
        PersonContainsKeywordsPredicate secondAddressPersonPredicate = new PersonContainsKeywordsPredicate(
                firstNamePredicate,
                firstProfilePredicate,
                firstPhonePredicate,
                firstEmailPredicate,
                secondAddressPredicate,
                firstLanguagePredicate,
                firstTagPredicate);
        PersonContainsKeywordsPredicate secondLanguagePersonPredicate = new PersonContainsKeywordsPredicate(
                firstNamePredicate,
                firstProfilePredicate,
                firstPhonePredicate,
                firstEmailPredicate,
                firstAddressPredicate,
                secondLanguagePredicate,
                firstTagPredicate);
        PersonContainsKeywordsPredicate secondTagPersonPredicate = new PersonContainsKeywordsPredicate(
                firstNamePredicate,
                firstProfilePredicate,
                firstPhonePredicate,
                firstEmailPredicate,
                firstAddressPredicate,
                firstLanguagePredicate,
                secondTagPredicate);

        FindCommand findFirstCommand = new FindCommand(firstPersonPredicate);

        FindCommand findNameSecondCommand = new FindCommand(secondNamePersonPredicate);
        FindCommand findProfileSecondCommand = new FindCommand(secondProfilePersonPredicate);
        FindCommand findPhoneSecondCommand = new FindCommand(secondPhonePersonPredicate);
        FindCommand findEmailSecondCommand = new FindCommand(secondEmailPersonPredicate);
        FindCommand findAddressSecondCommand = new FindCommand(secondAddressPersonPredicate);
        FindCommand findLanguageSecondCommand = new FindCommand(secondLanguagePersonPredicate);
        FindCommand findTagSecondCommand = new FindCommand(secondTagPersonPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findNameSecondCommand.equals(findNameSecondCommand));
        assertTrue(findProfileSecondCommand.equals(findProfileSecondCommand));
        assertTrue(findPhoneSecondCommand.equals(findPhoneSecondCommand));
        assertTrue(findEmailSecondCommand.equals(findEmailSecondCommand));
        assertTrue(findAddressSecondCommand.equals(findAddressSecondCommand));
        assertTrue(findLanguageSecondCommand.equals(findLanguageSecondCommand));
        assertTrue(findTagSecondCommand.equals(findTagSecondCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPersonPredicate);
        FindCommand findNameSecondCommandCopy = new FindCommand(secondNamePersonPredicate);
        FindCommand findProfileSecondCommandCopy = new FindCommand(secondProfilePersonPredicate);
        FindCommand findPhoneSecondCommandCopy = new FindCommand(secondPhonePersonPredicate);
        FindCommand findEmailSecondCommandCopy = new FindCommand(secondEmailPersonPredicate);
        FindCommand findAddressSecondCommandCopy = new FindCommand(secondAddressPersonPredicate);
        FindCommand findLanguageSecondCommandCopy = new FindCommand(secondLanguagePersonPredicate);
        FindCommand findTagSecondCommandCopy = new FindCommand(secondTagPersonPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        assertTrue(findNameSecondCommand.equals(findNameSecondCommandCopy));
        assertTrue(findProfileSecondCommand.equals(findProfileSecondCommandCopy));
        assertTrue(findPhoneSecondCommand.equals(findPhoneSecondCommandCopy));
        assertTrue(findEmailSecondCommand.equals(findEmailSecondCommandCopy));
        assertTrue(findAddressSecondCommand.equals(findAddressSecondCommandCopy));
        assertTrue(findLanguageSecondCommand.equals(findLanguageSecondCommandCopy));
        assertTrue(findTagSecondCommand.equals(findTagSecondCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findNameSecondCommand.equals(1));
        assertFalse(findProfileSecondCommand.equals(1));
        assertFalse(findPhoneSecondCommand.equals(1));
        assertFalse(findEmailSecondCommand.equals(1));
        assertFalse(findAddressSecondCommand.equals(1));
        assertFalse(findLanguageSecondCommand.equals(1));
        assertFalse(findTagSecondCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findNameSecondCommand.equals(null));
        assertFalse(findProfileSecondCommand.equals(null));
        assertFalse(findPhoneSecondCommand.equals(null));
        assertFalse(findEmailSecondCommand.equals(null));
        assertFalse(findAddressSecondCommand.equals(null));
        assertFalse(findLanguageSecondCommand.equals(null));
        assertFalse(findTagSecondCommand.equals(null));

        // different findCommand -> returns false
        assertFalse(findFirstCommand.equals(findNameSecondCommand));
        assertFalse(findFirstCommand.equals(findProfileSecondCommand));
        assertFalse(findFirstCommand.equals(findPhoneSecondCommand));
        assertFalse(findFirstCommand.equals(findEmailSecondCommand));
        assertFalse(findFirstCommand.equals(findAddressSecondCommand));
        assertFalse(findFirstCommand.equals(findLanguageSecondCommand));
        assertFalse(findFirstCommand.equals(findTagSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
