package vimification.internal.command.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import vimification.TestUtil;
import vimification.common.core.Index;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class DeleteFieldsCommandTest {

    @Test
    public void deleteDeadlineOnly_shouldWorkCorrectly() {
        DeleteFieldsRequest request = new DeleteFieldsRequest();
        request.setDeleteDeadline(true);

        Task task = TestUtil.newTask();
        task.setDeadline(LocalDateTime.now());
        task.addLabel("EoSD");
        task.addLabel("LoLK");
        int numOfLabels = task.getLabels().size();

        LogicTaskList taskList = TestUtil.newLogicTaskListStub(task);
        CommandStack commandStack = TestUtil.newCommandStack();

        DeleteFieldsCommand command = new DeleteFieldsCommand(Index.fromOneBased(1), request);
        command.execute(taskList, commandStack);
        Task newTask = taskList.get(0);

        // should not change the size of the task list
        assertEquals(1, taskList.size());
        assertEquals(1, commandStack.size());
        assertEquals(command, commandStack.pop());
        assertEquals(numOfLabels, task.getLabels().size());
        assertNull(newTask.getDeadline());

        command.undo(taskList);
        Task oldTask = taskList.get(0);

        assertEquals(1, taskList.size());
        assertEquals(numOfLabels, task.getLabels().size());
        assertNotNull(oldTask.getDeadline());
    }

    @Test
    public void deleteLabelOnly_shouldWorkCorrectly() {
        DeleteFieldsRequest request = new DeleteFieldsRequest();
        String label = "AoCF";
        LocalDateTime deadline = LocalDateTime.now();
        request.getDeletedLabels().add(label);

        Task task = TestUtil.newTask();
        task.setDeadline(deadline);
        task.addLabel(label);
        task.addLabel("LoLK");
        int numOfLabels = task.getLabels().size();

        LogicTaskList taskList = TestUtil.newLogicTaskListStub(task);
        CommandStack commandStack = TestUtil.newCommandStack();

        DeleteFieldsCommand command = new DeleteFieldsCommand(Index.fromOneBased(1), request);
        command.execute(taskList, commandStack);
        Task newTask = taskList.get(0);

        assertEquals(1, taskList.size());
        assertEquals(numOfLabels - 1, newTask.getLabels().size());
        assertEquals(deadline, newTask.getDeadline());
        assertFalse(newTask.containsLabel(label));

        command.undo(taskList);
        Task oldTask = taskList.get(0);

        assertEquals(1, taskList.size());
        assertEquals(numOfLabels, oldTask.getLabels().size());
        assertTrue(oldTask.containsLabel(label));
    }
}
