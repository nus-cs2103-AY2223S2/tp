package tfifteenfour.clipboard.logic.commands.editcommand;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.testutil.TypicalModel;


public class EditTaskCommandTest {
    private Model model;
    private Task originalTask;
    private Task newTask;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.TASK_PAGE);
        originalTask = model.getCurrentSelection().getSelectedTask();
        newTask = new Task("New Task");
    }

    @Test
    public void execute_validIndexAndNewTaskName_success() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST, newTask);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_SUCCESS, originalTask, newTask);
        Model expectedModel = model.copy();

        Group selectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        selectedGroup.setTask(selectedGroup.getUnmodifiableTaskList().get(INDEX_FIRST.getZeroBased()), newTask);
        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicateCourse_throwsCommandException() {
        Task firstTask = model.getCurrentSelection().getSelectedGroup()
                .getUnmodifiableTaskList()
                .get(INDEX_FIRST.getZeroBased());
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_SECOND, firstTask);

        assertThrows(CommandException.class, () -> editTaskCommand.execute(model));
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_OUT_OF_BOUND, newTask);

        assertThrows(CommandException.class, () -> editTaskCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        model.getCurrentSelection().setCurrentPage(PageType.STUDENT_PAGE);
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST, newTask);

        assertThrows(CommandException.class, () -> editTaskCommand.execute(model));
    }
}

