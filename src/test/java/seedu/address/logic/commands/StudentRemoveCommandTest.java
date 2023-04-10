package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
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

public class StudentRemoveCommandTest {
    private Model model;
    private SessionName sessionName1 = new SessionName("Hall");
    private SessionName sessionName2 = new SessionName("Ball");

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithSessions(), new UserPrefs());
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentRemoveCommand(null, new SessionName("Session")));
    }

    @Test
    public void constructor_nullSessionName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentRemoveCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        StudentRemoveCommand command =
                new StudentRemoveCommand(Index.fromOneBased(model.getFilteredPersonList().size() + 1),
                        new SessionName("Session"));
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_nonExistentSession_throwsCommandException() {
        StudentRemoveCommand command =
                new StudentRemoveCommand(INDEX_FIRST_PERSON,
                        new SessionName("Nonexistent session"));
        assertCommandFailure(command, model,
                String.format(StudentRemoveCommand.SESSION_NOT_FOUND_FAILURE, "Nonexistent Session"));
    }

    @Test
    public void execute_studentNotInSession_throwsCommandException() {
        StudentRemoveCommand command = new StudentRemoveCommand(INDEX_FIRST_PERSON, sessionName1);
        assertCommandFailure(command, model, StudentRemoveCommand.STUDENT_NOT_FOUND_FAILURE);
    }

    @Test
    public void execute_validCommand_success() throws CommandException {
        Person student = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        SessionName sessionName = model.getAddressBook().getSessionList().get(0).getSessionName();
        model.addPersonToSession(student, model.getSessionFromName(sessionName));
        StudentRemoveCommand command = new StudentRemoveCommand(INDEX_FIRST_PERSON, sessionName);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removePersonFromSession(student, expectedModel.getSessionFromName(sessionName));
        expectedModel.commitAddressBook();
        String expectedMessage =
                String.format(StudentRemoveCommand.SESSION_REMOVE_PERSON_SUCCESS,
                        student.getName(), model.getSessionFromName(sessionName));
        assertEquals(command.execute(model), new CommandResult(expectedMessage));
    }

    @Test
    public void equals() {
        StudentRemoveCommand command1 = new StudentRemoveCommand(INDEX_FIRST_PERSON, sessionName1);
        StudentRemoveCommand command2 = new StudentRemoveCommand(INDEX_FIRST_PERSON, sessionName1);
        StudentRemoveCommand command3 = new StudentRemoveCommand(INDEX_SECOND_PERSON, sessionName2);

        assertTrue(command1.equals(command1));
        assertTrue(command1.equals(command2));
        assertFalse(command1.equals(null));
        assertFalse(command1.equals(""));
        assertFalse(command1.equals(command3));
    }
}
