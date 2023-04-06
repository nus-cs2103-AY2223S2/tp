package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.testutil.TypicalPersons.CARL;
import static seedu.socket.testutil.TypicalPersons.ELLE;
import static seedu.socket.testutil.TypicalPersons.FIONA;
import static seedu.socket.testutil.TypicalPersons.getTypicalSocket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.person.predicate.FindCommandPersonPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalSocket(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSocket(), new UserPrefs());

    @Test
    public void testEquals() {
        List<String> firstSingletonList = Collections.singletonList("first");
        List<String> secondSingletonList = Collections.singletonList("second");
        FindCommandPersonPredicate firstPersonPredicate = new FindCommandPersonPredicate(
                        firstSingletonList,
                        firstSingletonList,
                        firstSingletonList,
                        firstSingletonList,
                        firstSingletonList,
                        firstSingletonList,
                        firstSingletonList);
        FindCommandPersonPredicate secondNamePersonPredicate = new FindCommandPersonPredicate(
                secondSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList);
        FindCommandPersonPredicate secondProfilePersonPredicate = new FindCommandPersonPredicate(
                firstSingletonList,
                secondSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList);
        FindCommandPersonPredicate secondPhonePersonPredicate = new FindCommandPersonPredicate(
                firstSingletonList,
                firstSingletonList,
                secondSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList);
        FindCommandPersonPredicate secondEmailPersonPredicate = new FindCommandPersonPredicate(
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                secondSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList);
        FindCommandPersonPredicate secondAddressPersonPredicate = new FindCommandPersonPredicate(
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                secondSingletonList,
                firstSingletonList,
                firstSingletonList);
        FindCommandPersonPredicate secondLanguagePersonPredicate = new FindCommandPersonPredicate(
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                secondSingletonList,
                firstSingletonList);
        FindCommandPersonPredicate secondTagPersonPredicate = new FindCommandPersonPredicate(
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                firstSingletonList,
                secondSingletonList);

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
        FindCommandPersonPredicate predicate = preparePersonPredicate(" ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommandPersonPredicate predicate = preparePersonPredicate("Kurz Elle Kunz",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code FindCommandPersonPredicate}.
     */
    private FindCommandPersonPredicate preparePersonPredicate(String nameInput,
                                                              String profileInput,
                                                              String phoneInput,
                                                              String emailInput,
                                                              String addressInput,
                                                              String languageInput,
                                                              String tagInput) {
        List<String> nameList = Arrays.asList(nameInput.split("\\s+"));
        List<String> profileList = Arrays.asList(profileInput.split("\\s+"));
        List<String> phoneList = Arrays.asList(phoneInput.split("\\s+"));
        List<String> emailList = Arrays.asList(emailInput.split("\\s+"));
        List<String> addressList = Arrays.asList(addressInput.split("\\s+"));
        List<String> languageList = Arrays.asList(languageInput.split("\\s+"));
        List<String> tagList = Arrays.asList(tagInput.split("\\s+"));

        return new FindCommandPersonPredicate(nameList,
                profileList,
                phoneList,
                emailList,
                addressList,
                languageList,
                tagList);
    }

}
