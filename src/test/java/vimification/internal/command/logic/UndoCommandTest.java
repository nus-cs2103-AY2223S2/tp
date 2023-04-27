package vimification.internal.command.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import vimification.TestUtil;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class UndoCommandTest {

    @Test
    public void shouldWorkCorrectly() {
        LogicTaskList taskList = TestUtil.newLogicTaskListStub();
        CommandStack commandStack = TestUtil.newCommandStack();
        Task task = TestUtil.newTask();
        int numTasks = 2;
        for (int i = 0; i < numTasks; i++) {
            new AddCommand(task).execute(taskList, commandStack);
        }

        assertEquals(numTasks, taskList.size());
        assertEquals(numTasks, commandStack.size());

        UndoCommand command = new UndoCommand();
        for (int i = numTasks - 1; i >= 0; i--) {
            command.execute(taskList, commandStack);

            assertEquals(i, taskList.size());
            assertEquals(i, commandStack.size());
        }
    }
}
