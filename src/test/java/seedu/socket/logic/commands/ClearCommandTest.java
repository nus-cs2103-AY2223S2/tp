package seedu.socket.logic.commands;

import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.testutil.TypicalPersons.getTypicalSocket;

import org.junit.jupiter.api.Test;

import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.Socket;
import seedu.socket.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptySocket_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySocket_success() {
        Model model = new ModelManager(getTypicalSocket(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSocket(), new UserPrefs());
        expectedModel.setSocket(new Socket());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
