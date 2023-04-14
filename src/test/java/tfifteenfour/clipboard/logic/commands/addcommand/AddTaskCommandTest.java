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
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.testutil.TypicalModel;

class AddTaskCommandTest {
    private Model model;
    private Model expectedModel;
    private Group selectedGroup;
    private Task selectedTask;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedTask = model.getCurrentSelection().getSelectedTask();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.TASK_PAGE);
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() {
        Task validTask = new Task("New Task");

        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        String expectedMessage = String.format(AddTaskCommand.MESSAGE_SUCCESS, selectedGroup, validTask);

        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        expectedSelectedGroup.addTask(validTask);

        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);

        Task validTask = new Task("New Task");
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_WRONG_PAGE, () -> addTaskCommand.execute(model));
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task existingTask = selectedTask;

        AddTaskCommand commandCopy = new AddTaskCommand(new Task(existingTask.getTaskName()));
        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> commandCopy.execute(model));
    }
}
