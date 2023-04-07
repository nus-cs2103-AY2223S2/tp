package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.HMHero;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
@SuppressWarnings("checkstyle:CommentsIndentation")
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personDoesNotExistInList_throwsCommandException() throws CommandException {
        Model expectedModel = new ModelManager(new HMHero(model.getAddressBook()), new UserPrefs());
        Person personToDelete = new PersonBuilder().withName("Thomas Chew").withPhone("92223333").build();
        String expectedMessage = Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE;
        DeleteCommand deleteCommand = new DeleteCommand(new NamePhoneNumberPredicate(personToDelete.getName(),
                personToDelete.getPhone()));
        Throwable exception = assertThrows(CommandException.class, () -> deleteCommand.execute(expectedModel));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void execute_personExistsInList_success() throws CommandException {
        Model expectedModel = new ModelManager(new HMHero(model.getAddressBook()), new UserPrefs());
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(new NamePhoneNumberPredicate(personToDelete.getName(),
                personToDelete.getPhone()));
        CommandResult expectedResult = deleteCommand.execute(expectedModel);
        assertCommandSuccess(deleteCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        NamePhoneNumberPredicate alicePredicate = new NamePhoneNumberPredicate(alice.getName(), alice.getPhone());
        NamePhoneNumberPredicate bobPredicate = new NamePhoneNumberPredicate(bob.getName(), bob.getPhone());
        DeleteCommand deleteFirstCommand = new DeleteCommand(alicePredicate);
        DeleteCommand deleteSecondCommand = new DeleteCommand(bobPredicate);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(alicePredicate);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
