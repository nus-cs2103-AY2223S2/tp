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

class RedoCommandTest {

    @Test
    public void execute_noUndoneChangesToRevert_throwsCommandException() {
        Model model = new ModelManager();
        RedoCommand redoCommand = new RedoCommand();
        assertThrows(CommandException.class, RedoCommand.MESSAGE_CANNOT_REDO, () -> redoCommand.execute(model));
    }

    @Test
    public void execute_hasUndoneChangesToRevert_success() {
        RedoCommand redoCommand = new RedoCommand();

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);

        model.undoSocket();
        expectedModel.undoSocket();

        expectedModel.redoSocket();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hasMultipleUndoneChangesToRevert_success() {
        RedoCommand redoCommand = new RedoCommand();

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);
        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);

        model.undoSocket();
        model.undoSocket();
        expectedModel.undoSocket();
        expectedModel.undoSocket();

        expectedModel.redoSocket();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        expectedModel.redoSocket();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
