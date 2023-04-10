
package tfifteenfour.clipboard.logic.commands.taskcommand;

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

public class TaskCommandTest {
    private Model model;
    private Group selectedGroup;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.TASK_STUDENT_PAGE);
        selectedGroup = model.getCurrentSelection().getSelectedGroup();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
    }

    @Test
    public void execute_validIndex_success() {
        TaskCommand TaskCommand = new TaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(TaskCommand.MESSAGE_SUCCESS, selectedGroup);
        Model expectedModel = model.copy();

        assertCommandSuccess(TaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        TaskCommand TaskCommand = new TaskCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> TaskCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        TaskCommand TaskCommand = new TaskCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> TaskCommand.execute(model));
    }

    @Test
    public void equals() {
        TaskCommand command1 = new TaskCommand(INDEX_FIRST);
        TaskCommand command2 = new TaskCommand(INDEX_FIRST);
        TaskCommand command3 = new TaskCommand(INDEX_SECOND);

        SelectCommand differentCommand = new SelectCommand(INDEX_FIRST);

        // Test for equality
        assertEquals(command1, command2);
        assertEquals(command2, command1);

        assertNotEquals(command3, command1);
        assertNotEquals(command1, command3);
        assertNotEquals(command1, differentCommand);
    }
}
