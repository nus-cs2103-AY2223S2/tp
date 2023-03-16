package seedu.socket.logic.commands;

import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.logic.commands.CommandTestUtil.deletePersonIndexOne;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.AddressBook;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;

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
