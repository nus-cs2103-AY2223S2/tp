package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithSessions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.session.SessionName;

public class StudentAddCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithSessions(), new UserPrefs());
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new StudentAddCommand(null, new SessionName("Session")));
    }

    @Test
    public void constructor_nullSessionName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new StudentAddCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        StudentAddCommand command =
                new StudentAddCommand(
                        Index.fromOneBased(model.getFilteredPersonList().size() + 1),
                        new SessionName("Session"));
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_nonExistentSession_throwsCommandException() {
        StudentAddCommand command = new StudentAddCommand(INDEX_FIRST_PERSON, new SessionName("Nonexistent session"));
        assertCommandFailure(command, model,
                String.format(StudentAddCommand.SESSION_NOT_FOUND_FAILURE,
                        "Nonexistent Session", model.getAddressBook().getSessionList()));
    }

    @Test
    public void execute_studentAlreadyInSession_throwsCommandException() {
        Person student = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        SessionName sessionName = model.getAddressBook().getSessionList().get(0).getSessionName();
        model.addPersonToSession(student, model.getSessionFromName(sessionName));
        StudentAddCommand command = new StudentAddCommand(INDEX_FIRST_PERSON, sessionName);
        assertCommandFailure(command, model,
                String.format(StudentAddCommand.STUDENT_ALREADY_ADDED_FAILURE, sessionName));
    }

    @Test
    public void execute_validCommand_success() throws CommandException {
        Person student = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        SessionName sessionName = model.getAddressBook().getSessionList().get(0).getSessionName();
        StudentAddCommand command = new StudentAddCommand(INDEX_FIRST_PERSON, sessionName);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPersonToSession(student, expectedModel.getSessionFromName(sessionName));
        expectedModel.commitAddressBook();
        String expectedMessage = String.format(StudentAddCommand.SESSION_ADD_PERSON_SUCCESS,
                student.getName(), model.getSessionFromName(sessionName));
        assertEquals(command.execute(model), new CommandResult(expectedMessage));
    }
}
