package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.TypicalPersons;

public class MarkAttendanceCommandTest {
    private final Index index1 = Index.fromOneBased(1);
    private final Index index2 = Index.fromOneBased(2);
    private final Name name1 = new Name("John Doe");
    private final Name name2 = new Name("Jane Smith");
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private Name personName = new Name("John Doe");
    private Person alice = TypicalPersons.ALICE;
    private Session session = SessionBuilder.generateDefaultSession();

    @Test
    public void execute_validIndexValidPersonName_success() throws Exception {
        session.addPersonToSession(alice);
        session.markStudentPresent(personName.formattedName);
        model.addSession(session);
        expectedModel.addSession(session);

        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(Index.fromZeroBased(0), personName);
        CommandResult commandResult = markAttendanceCommand.execute(model);
        assertEquals(String.format(MarkAttendanceCommand.MESSAGE_SUCCESS,
                personName, session.getName()), commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_invalidSessionIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSessionList().size() + 1);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(outOfBoundIndex, personName);
        assertThrows(CommandException.class, () ->
                markAttendanceCommand.execute(model));
        assertEquals(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX, assertThrows(CommandException.class, () ->
                markAttendanceCommand.execute(model)).getMessage());
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        model.addSession(session);
        expectedModel.addSession(session);

        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(Index.fromZeroBased(0), personName);
        assertThrows(CommandException.class, () -> markAttendanceCommand.execute(model));
        assertEquals(MarkAttendanceCommand.MESSAGE_PERSON_NOT_FOUND,
                assertThrows(CommandException.class, () -> markAttendanceCommand.execute(model)).getMessage());
    }

    @Test
    public void equals() {
        MarkAttendanceCommand markAttendanceCommand1 = new MarkAttendanceCommand(index1, name1);

        // same object -> returns true
        assertEquals(markAttendanceCommand1, markAttendanceCommand1);

        // same values -> returns true
        MarkAttendanceCommand markAttendanceCommand1Copy = new MarkAttendanceCommand(index1, name1);
        assertEquals(markAttendanceCommand1, markAttendanceCommand1Copy);

        // different index -> returns false
        MarkAttendanceCommand markAttendanceCommand2 = new MarkAttendanceCommand(index2, name1);
        assertNotEquals(markAttendanceCommand1, markAttendanceCommand2);

        // different name -> returns false
        MarkAttendanceCommand markAttendanceCommand3 = new MarkAttendanceCommand(index1, name2);
        assertNotEquals(markAttendanceCommand1, markAttendanceCommand3);

        // different types -> returns false
        assertNotEquals(markAttendanceCommand1, 1);

        // null -> returns false
        assertNotEquals(markAttendanceCommand1, null);
    }
}

