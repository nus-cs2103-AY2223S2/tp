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

//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastSession = Index.fromOneBased(actualSelectedGroup.getUnmodifiableSessionList().size());
//        Session lastSession = actualSelectedGroup.getUnmodifiableSessionList().get(indexLastSession.getZeroBased());
//        SessionBuilder sessionInList = new SessionBuilder(lastSession);
//        Session editedSession = sessionInList.withSessionName("New Session").build();
//        EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder().withSessionName("New Session").build();
//        EditSessionCommand editSessionCommand = new EditSessionCommand(indexLastSession, descriptor);
//        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS, editedSession);
//        Model expectedModel = model.copy();
//        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
//        expectedSelectedGroup.setSession(expectedSelectedGroup.getUnmodifiableFilteredSessionList()
//                .get(indexLastSession.getZeroBased()), editedSession);
//        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST_SESSION, new EditSessionDescriptor());
//        Session editedSession = actualSelectedGroup.getUnmodifiableFilteredSessionList().get(INDEX_FIRST_SESSION.getZeroBased());
//        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS, editedSession);
//        Model expectedModel = model.copy();
//        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showSessionAtIndex(model, INDEX_FIRST_SESSION);
//        Session sessionInFilteredList = actualSelectedGroup.getUnmodifiableFilteredSessionList()
//                .get(INDEX_FIRST_SESSION.getZeroBased());
//        Session editedSession = new SessionBuilder(sessionInFilteredList).withSessionName("New Session").build();
//        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST_SESSION,
//                new EditSessionDescriptorBuilder().withSessionName("New Session").build());
//        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS, editedSession);
//        Model expectedModel = model.copy();
//        showSessionAtIndex(expectedModel, INDEX_FIRST_SESSION);
//        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
//        expectedSelectedGroup.setSession(expectedSelectedGroup.getUnmodifiableFilteredSessionList().get(
//                assertCommandFailure(editStudentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }

//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidSessionIndexFilteredList_failure() {
//        Index outOfBoundIndex = INDEX_OUT_OF_BOUND;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < actualSelectedGroup.getUnmodifiableStudentList().size());
//
//        EditStudentCommand editStudentCommand = new EditStudentCommand(outOfBoundIndex,
//                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        assertCommandFailure(editStudentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }

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
