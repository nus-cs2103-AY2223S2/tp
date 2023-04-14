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
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.testutil.TypicalModel;

class DeleteTaskCommandTest {
    private Model model;
    private Group selectedGroup;
    private Task selectedTask;
    private CurrentSelection actualSelection;


    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedTask = model.getCurrentSelection().getSelectedTask();
        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.TASK_PAGE);
    }

    @Test
    public void execute_validIndex_success() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_SUCCESS, selectedGroup,
                selectedTask);
        Model expectedModel = model.copy();
        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        expectedSelectedGroup.deleteTask(
                expectedSelectedGroup.getUnmodifiableTaskList().get(INDEX_FIRST.getZeroBased()));

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> deleteTaskCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> deleteTaskCommand.execute(model));
    }

}
