package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CreateSessionCommand.MESSAGE_DUPLICATE_SESSION;
import static seedu.address.logic.commands.CreateSessionCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.SessionBuilder.SESSION_ONE;
import static seedu.address.testutil.SessionBuilder.SESSION_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.session.Session;
import seedu.address.testutil.SessionBuilder;

public class CreateSessionCommandTest {
    private Model model = new ModelManager();
    private Session validSession = SessionBuilder.generateDefaultSession();

    @Test
    public void constructor_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateSessionCommand(null));
    }

    @Test
    public void execute_sessionAcceptedByModel_addSuccessful() throws Exception {
        CreateSessionCommand command = new CreateSessionCommand(validSession);
        String expectedMessage = String.format(MESSAGE_SUCCESS, validSession);

        CommandResult commandResult = command.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertTrue(model.hasSession(validSession));
    }

    @Test
    public void execute_duplicateSession_throwsCommandException() {
        model.addSession(validSession);
        CreateSessionCommand command = new CreateSessionCommand(validSession);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_SESSION, () -> command.execute(model));
    }

    @Test
    public void equals() {
        CreateSessionCommand addSessionOneCommand = new CreateSessionCommand(SESSION_ONE);
        CreateSessionCommand addSessionTwoCommand = new CreateSessionCommand(SESSION_TWO);

        // same object -> returns true
        assertTrue(addSessionOneCommand.equals(addSessionOneCommand));

        // same values -> returns true
        CreateSessionCommand addSessionOneCommandCopy = new CreateSessionCommand(SESSION_ONE);
        assertTrue(addSessionOneCommand.equals(addSessionOneCommandCopy));

        // different types -> returns false
        assertFalse(addSessionOneCommand.equals(1));

        // null -> returns false
        assertFalse(addSessionOneCommand.equals(null));

        // different session -> returns false
        assertFalse(addSessionOneCommand.equals(addSessionTwoCommand));
    }
}

