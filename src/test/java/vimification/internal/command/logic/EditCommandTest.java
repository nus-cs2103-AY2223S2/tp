package vimification.internal.command.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import vimification.TestUtil;
import vimification.common.core.Index;
import vimification.internal.parser.Pair;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;

public class EditCommandTest {

    @Test
    public void editDeadlineOnly_shouldWorkCorrectly() {
        EditRequest request = new EditRequest();
        LocalDateTime newDeadline = LocalDateTime.now();
        request.setEditedDeadline(newDeadline);

        Task task = TestUtil.newTask();
        task.addLabel("EoSD");
        task.addLabel("LoLK");
        int numOfLabels = task.getLabels().size();

        LogicTaskList taskList = TestUtil.newLogicTaskListStub(task);
        CommandStack commandStack = TestUtil.newCommandStack();

        EditCommand command = new EditCommand(Index.fromOneBased(1), request);
        command.execute(taskList, commandStack);
        Task newTask = taskList.get(0);

        // should not change the size of the task list
        assertEquals(1, taskList.size());
        assertEquals(1, commandStack.size());
        assertEquals(command, commandStack.pop());
        assertEquals(numOfLabels, task.getLabels().size());
        assertEquals(newDeadline, newTask.getDeadline());

        command.undo(taskList);
        Task oldTask = taskList.get(0);

        assertEquals(1, taskList.size());
        assertEquals(task, oldTask);
    }

    @Test
    public void editLabelsOnly_shouldWorkCorrectly() {
        EditRequest request = new EditRequest();
        Pair<String, String> labels1 = Pair.of("AoCF", "EoSD");
        Pair<String, String> labels2 = Pair.of("LoLK", "IaMP");
        LocalDateTime deadline = LocalDateTime.now();
        request.getEditedLabels().add(labels1);
        request.getEditedLabels().add(labels2);

        Task task = TestUtil.newTask();
        task.setDeadline(deadline);
        task.addLabel(labels1.getFirst());
        task.addLabel(labels2.getFirst());
        int numOfLabels = task.getLabels().size();

        LogicTaskList taskList = TestUtil.newLogicTaskListStub(task);
        CommandStack commandStack = TestUtil.newCommandStack();

        EditCommand command = new EditCommand(Index.fromOneBased(1), request);
        command.execute(taskList, commandStack);
        Task newTask = taskList.get(0);

        assertEquals(1, taskList.size());
        assertEquals(numOfLabels, newTask.getLabels().size());
        assertEquals(deadline, newTask.getDeadline());
        assertFalse(newTask.containsLabel(labels1.getFirst()));
        assertFalse(newTask.containsLabel(labels2.getFirst()));
        assertTrue(newTask.containsLabel(labels1.getSecond()));
        assertTrue(newTask.containsLabel(labels2.getSecond()));

        command.undo(taskList);
        Task oldTask = taskList.get(0);
        assertEquals(task, oldTask);
    }

    @Test
    public void editPriorityOnly_shouldWorkCorrectly() {
        EditRequest request = new EditRequest();
        request.setEditedPriority(Priority.VERY_URGENT);

        LocalDateTime deadline = LocalDateTime.now();
        String title = "Touhou Project";

        Task task = TestUtil.newTask();
        task.setDeadline(deadline);
        task.setTitle(title);

        LogicTaskList taskList = TestUtil.newLogicTaskListStub(task);
        CommandStack commandStack = TestUtil.newCommandStack();

        EditCommand command = new EditCommand(Index.fromOneBased(1), request);
        command.execute(taskList, commandStack);
        Task newTask = taskList.get(0);

        assertEquals(deadline, newTask.getDeadline());
        assertEquals(title, newTask.getTitle());

        command.undo(taskList);
        Task oldTask = taskList.get(0);
        assertEquals(task, oldTask);
    }

    @Test
    public void editBothTitleAndStatus_shouldWorkCorrectly() {
        EditRequest request = new EditRequest();
        request.setEditedStatus(Status.COMPLETED);
        request.setEditedTitle("Spring Lane");

        LocalDateTime deadline = LocalDateTime.now();
        String title = "Rest In Peace, Saith The Lord";

        Task task = TestUtil.newTask();
        task.setDeadline(deadline);
        task.setTitle(title);
        task.setStatus(Status.NOT_DONE);

        LogicTaskList taskList = TestUtil.newLogicTaskListStub(task);
        CommandStack commandStack = TestUtil.newCommandStack();

        EditCommand command = new EditCommand(Index.fromOneBased(1), request);
        command.execute(taskList, commandStack);
        Task newTask = taskList.get(0);

        assertEquals(deadline, newTask.getDeadline());
        assertEquals(request.getEditedTitle(), newTask.getTitle());
        assertEquals(request.getEditedStatus(), newTask.getStatus());

        command.undo(taskList);
        Task oldTask = taskList.get(0);
        assertEquals(task, oldTask);
    }
}
