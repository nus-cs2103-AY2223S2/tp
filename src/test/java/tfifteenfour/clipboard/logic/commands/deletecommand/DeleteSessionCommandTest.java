package tfifteenfour.clipboard.logic.commands.deletecommand;


import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.testutil.TypicalModel;

class DeleteSessionCommandTest {
    private Model model;
    private Group selectedGroup;
    private Session selectedSession;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.SESSION_PAGE);
    }

    @Test
    public void execute_validIndex_success() {
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteSessionCommand.MESSAGE_SUCCESS, selectedGroup, selectedSession);
        Model expectedModel = model.copy();
        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        expectedSelectedGroup.deleteSession(
                expectedSelectedGroup.getUnmodifiableSessionList().get(INDEX_FIRST.getZeroBased()));

        assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> deleteSessionCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> deleteSessionCommand.execute(model));
    }

}
