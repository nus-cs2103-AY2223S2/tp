package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.testutil.PersonBuilder;

public class StateHistoryTest {

    @Test
    public void undoRedo_boundsExceeded_selfBounded() throws CommandException {
        Model model = new ModelManager();
        StateHistory stateHistory = new StateHistory(model);
        for (int i = 0; i < 5; i++) {
            AddCommand addCommand = new AddCommand(new PersonBuilder().withName(Integer.toString(i)).build());
            CommandResult result = addCommand.execute(model);
            stateHistory.offerCommand(addCommand, model, result);
        }
        assertEquals(stateHistory.undo(4), 4);
        assertEquals(stateHistory.undo(3), 1);
        assertEquals(stateHistory.redo(5), 5);
        assertEquals(stateHistory.redo(5), 0);
        assertEquals(stateHistory.undo(6), 5);
        assertEquals(stateHistory.redo(9001), 5);
    }

    @Test
    public void undo_addCommand_correctCount() throws CommandException {
        Model model = new ModelManager();
        StateHistory stateHistory = new StateHistory(model);
        for (int i = 0; i < 5; i++) {
            AddCommand addCommand = new AddCommand(new PersonBuilder().withName(Integer.toString(i)).build());
            CommandResult result = addCommand.execute(model);
            stateHistory.offerCommand(addCommand, model, result);
        }
        assertEquals(stateHistory.presentModel().getAddressBook().getPersonList().size(), 5);
        for (int i = 0; i < 5; ++i) {
            assertEquals(stateHistory.undo(1), 1);
            assertEquals(stateHistory.presentModel().getAddressBook().getPersonList().size(), 5 - i - 1);
        }
    }

    @Test
    public void undo_exceededInterval_correctCount() throws CommandException {
        Model model = new ModelManager();
        StateHistory stateHistory = new StateHistory(model);
        int n = StateHistory.HISTORY_DEFAULT_INTERVAL * 2 + 2;
        for (int i = 0; i < n; i++) {
            AddCommand addCommand = new AddCommand(new PersonBuilder().withName(Integer.toString(i)).build());
            CommandResult result = addCommand.execute(model);
            stateHistory.offerCommand(addCommand, model, result);
        }
        assertEquals(stateHistory.presentModel().getAddressBook().getPersonList().size(), n);
        for (int i = 0; i < n; ++i) {
            assertEquals(stateHistory.undo(1), 1);
            assertEquals(stateHistory.presentModel().getAddressBook().getPersonList().size(), n - i - 1);
        }
    }

    @Test
    public void undoRedo_splitFuture_dropOriginal() throws CommandException {
        Model model = new ModelManager();
        StateHistory stateHistory = new StateHistory(model);
        for (int i = 0; i < 16; i++) {
            AddCommand addCommand = new AddCommand(new PersonBuilder().withName(Integer.toString(i)).build());
            CommandResult result = addCommand.execute(model);
            stateHistory.offerCommand(addCommand, model, result);
        }
        assertEquals(stateHistory.undo(8), 8);
        model = stateHistory.presentModel();
        for (int i = 8; i < 10; i++) {
            AddCommand addCommand = new AddCommand(new PersonBuilder().withName(Integer.toString(i)).build());
            CommandResult result = addCommand.execute(model);
            stateHistory.offerCommand(addCommand, model, result);
        }
        assertEquals(stateHistory.presentModel().getAddressBook().getPersonList().size(), 10);
        assertEquals(stateHistory.redo(1), 0);
        assertEquals(stateHistory.undo(20), 10);
        assertEquals(stateHistory.redo(20), 10);
        assertEquals(stateHistory.presentModel().getAddressBook().getPersonList().size(), 10);
    }

}
