package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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

class SelectCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToSelect = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SELECT_PERSON_SUCCESS, personToSelect);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectCommand selectFirstCommand = new SelectCommand(INDEX_FIRST_PERSON);
        SelectCommand selectSecondCommand = new SelectCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(selectFirstCommand, selectFirstCommand);

        // same values -> returns true
        SelectCommand selectFirstCommandCopy = new SelectCommand(INDEX_FIRST_PERSON);
        assertEquals(selectFirstCommand, selectFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, selectFirstCommand);

        // null -> returns false
        assertNotEquals(null, selectFirstCommand);

        // different person -> returns false
        assertNotEquals(selectFirstCommand, selectSecondCommand);

    }
}
