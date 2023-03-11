package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.deletePersonIndexOne;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class UndoCommandTest {

    @Test
    public void execute_noChangesToRevert_throwsCommandException() {
        Model model = new ModelManager();
        UndoCommand undoCommand = new UndoCommand();
        assertThrows(CommandException.class, UndoCommand.MESSAGE_CANNOT_UNDO, () -> undoCommand.execute(model));
    }

    @Test
    public void execute_hasChangesToRevert_success() {
        UndoCommand undoCommand = new UndoCommand();

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);

        expectedModel.undoSocket();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hasMultipleChangesToRevert_success() {
        UndoCommand undoCommand = new UndoCommand();

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);
        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);

        expectedModel.undoSocket();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        expectedModel.undoSocket();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
