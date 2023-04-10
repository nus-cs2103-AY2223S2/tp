package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithSessions;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.session.Session;


class UnmarkAttendanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithSessions(), new UserPrefs());
    private Index validIndex = Index.fromOneBased(1);
    private Name validName = new Name("Alice Pauline");

    @Test
    void execute_validPersonInSession_success() throws Exception {
        Session sessionToEdit = model.getFilteredSessionList().get(validIndex.getZeroBased());
        sessionToEdit.markStudentPresent(validName.formattedName);
        model.setSession(sessionToEdit, sessionToEdit);
        model.commitAddressBook();
        UnmarkAttendanceCommand unmarkAttendanceCommand = new UnmarkAttendanceCommand(validIndex, validName);
        CommandResult commandResult = unmarkAttendanceCommand.execute(model);
        assertEquals(String.format(UnmarkAttendanceCommand.MESSAGE_SUCCESS, validName, sessionToEdit.getName()),
                commandResult.getFeedbackToUser());
    }

    @Test
    void execute_invalidPersonInSession_throwsCommandException() {
        UnmarkAttendanceCommand unmarkAttendanceCommand = new UnmarkAttendanceCommand(validIndex, new Name("Bob Choo"));
        assertThrows(CommandException.class, () -> unmarkAttendanceCommand.execute(model),
                UnmarkAttendanceCommand.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    void equals() {
        UnmarkAttendanceCommand unmarkAttendanceCommand = new UnmarkAttendanceCommand(validIndex, validName);

        // same object -> returns true
        assertTrue(unmarkAttendanceCommand.equals(unmarkAttendanceCommand));

        // same values -> returns true
        UnmarkAttendanceCommand unmarkAttendanceCommandCopy = new UnmarkAttendanceCommand(validIndex, validName);
        assertTrue(unmarkAttendanceCommand.equals(unmarkAttendanceCommandCopy));

        // different types -> returns false
        assertFalse(unmarkAttendanceCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkAttendanceCommand.equals(null));

        // different person name -> returns false
        Name differentName = new Name("Bobby Choo");
        UnmarkAttendanceCommand unmarkAttendanceCommandDifferentName =
                new UnmarkAttendanceCommand(validIndex, differentName);
        assertFalse(unmarkAttendanceCommand.equals(unmarkAttendanceCommandDifferentName));

        // different session index -> returns false
        Index differentIndex = Index.fromZeroBased(1);
        UnmarkAttendanceCommand unmarkAttendanceCommandDifferentIndex =
                new UnmarkAttendanceCommand(differentIndex, validName);
        assertFalse(unmarkAttendanceCommand.equals(unmarkAttendanceCommandDifferentIndex));
    }
}
