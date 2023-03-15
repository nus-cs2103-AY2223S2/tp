package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CheckCommand}.
 */
class CheckCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validCheckIndexList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToCheck = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CheckCommand checkCommand = new CheckCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CheckCommand.MESSAGE_CHECK_PERSON_SUCCESS, personToCheck);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.checkPerson(personToCheck);

        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(checkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCheckIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CheckCommand checkCommand = new CheckCommand(outOfBoundIndex);

        assertCommandFailure(checkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidCheckIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        CheckCommand checkCommand = new CheckCommand(outOfBoundIndex);

        assertCommandFailure(checkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CheckCommand checkFirstCommand = new CheckCommand(INDEX_FIRST_PERSON);
        CheckCommand checkSecondCommand = new CheckCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(checkFirstCommand.equals(checkFirstCommand));

        // same values -> returns true
        CheckCommand checkFirstCommandCopy = new CheckCommand(INDEX_FIRST_PERSON);
        assertTrue(checkFirstCommand.equals(checkFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(checkFirstCommand.equals(checkSecondCommand));
    }
}
