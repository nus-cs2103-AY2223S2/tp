package tfifteenfour.clipboard.logic.commands.addcommand;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.testutil.TypicalModel;

class AddSessionCommandTest {
    private Model model;
    private Model expectedModel;
    private Group selectedGroup;
    private Session selectedSession;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.SESSION_PAGE);
    }

    @Test
    public void execute_sessionAcceptedByModel_addSuccessful() {
        Session validSession = new Session("New Session");

        AddSessionCommand addSessionCommand = new AddSessionCommand(validSession);
        String expectedMessage = String.format(AddSessionCommand.MESSAGE_SUCCESS, selectedGroup, validSession);

        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        expectedSelectedGroup.addSession(validSession);

        assertCommandSuccess(addSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);

        Session validSession = new Session("New Session");
        AddSessionCommand addSessionCommand = new AddSessionCommand(validSession);

        assertThrows(CommandException.class, AddSessionCommand.MESSAGE_WRONG_PAGE,
                () -> addSessionCommand.execute(model));
    }

    @Test
    public void execute_duplicateSession_throwsCommandException() {
        Session existingSession = selectedSession;

        AddSessionCommand commandCopy = new AddSessionCommand(new Session(existingSession.getSessionName()));
        assertThrows(CommandException.class, AddSessionCommand.MESSAGE_DUPLICATE_SESSION,
                () -> commandCopy.execute(model));
    }
}
