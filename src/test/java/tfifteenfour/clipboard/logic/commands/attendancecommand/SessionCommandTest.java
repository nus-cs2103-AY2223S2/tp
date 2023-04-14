package tfifteenfour.clipboard.logic.commands.attendancecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.SelectCommand;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class SessionCommandTest {
    private Model model;
    private Group selectedGroup;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        selectedGroup = model.getCurrentSelection().getSelectedGroup();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
    }

    @Test
    public void execute_validIndex_success() {
        SessionCommand sessionCommand = new SessionCommand(INDEX_FIRST);

        String expectedMessage = String.format(SessionCommand.MESSAGE_SUCCESS, selectedGroup);
        Model expectedModel = model.copy();

        assertCommandSuccess(sessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        SessionCommand sessionCommand = new SessionCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> sessionCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        SessionCommand sessionCommand = new SessionCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> sessionCommand.execute(model));
    }

    @Test
    public void equals() {
        SessionCommand command1 = new SessionCommand(INDEX_FIRST);
        SessionCommand command2 = new SessionCommand(INDEX_FIRST);
        SessionCommand command3 = new SessionCommand(INDEX_SECOND);

        SelectCommand differentCommand = new SelectCommand(INDEX_FIRST);

        // Test for equality
        assertEquals(command1, command2);
        assertEquals(command2, command1);

        assertNotEquals(command3, command1);
        assertNotEquals(command1, command3);
        assertNotEquals(command1, differentCommand);

    }
}

