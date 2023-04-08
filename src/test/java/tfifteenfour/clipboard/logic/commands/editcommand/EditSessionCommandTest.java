package tfifteenfour.clipboard.logic.commands.editcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.DESC_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.DESC_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.ClearCommand;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.parser.EditCommandParser.EditStudentDescriptor;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class EditSessionCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_PAGE);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Session sessionToEdit = model.getCurrentSelection().getSelectedSession();
        Session editedSession = new Session("NewSession");

        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST, editedSession);
        String expectedMessage = String.format(EditSessionCommand.MESSAGE_SUCCESS, sessionToEdit, editedSession);
        Model expectedModel = model.copy();
        Group selectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        selectedGroup.setSession(selectedGroup.getUnmodifiableFilteredSessionList().get(0), editedSession);
        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_onWrongPage_failure() {
        model.getCurrentSelection().setCurrentPage(PageType.STUDENT_PAGE);
        Session editedSession = new Session("NewSession");

        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST, editedSession);
        assertThrows(CommandException.class, () -> editSessionCommand.execute(model));
    }

    @Test
    public void execute_indexOutOfBound_failure() {
        Session editedSession = new Session("NewSession");

        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_OUT_OF_BOUND, editedSession);
        assertThrows(CommandException.class, () -> editSessionCommand.execute(model));
    }

    @Test
    public void execute_duplicateSessionList_failure() {
        Session secondSession = model.getCurrentSelection().getSelectedGroup()
                .getUnmodifiableSessionList()
                .get(INDEX_SECOND.getZeroBased());

        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST, secondSession);
        assertThrows(CommandException.class, () -> editSessionCommand.execute(model));
    }



    @Test
    public void equals() {
        final EditStudentCommand standardCommand = new EditStudentCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditStudentCommand commandWithSameValues = new EditStudentCommand(INDEX_FIRST, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditStudentCommand(INDEX_SECOND, DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditStudentCommand(INDEX_FIRST, DESC_BOB));
    }
}
