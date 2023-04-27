package vimification.internal.command.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import vimification.TestUtil;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class AddCommandTest {

    @Test
    public void shouldWorkCorrectly() {
        LogicTaskList taskList = TestUtil.newLogicTaskListStub();
        CommandStack commandStack = TestUtil.newCommandStack();
        Task task = TestUtil.newTask();

        AddCommand command = new AddCommand(task);
        command.execute(taskList, commandStack);

        // taskList should have 1 task now
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
        assertEquals(1, commandStack.size());
        assertEquals(command, commandStack.pop());

        command.undo(taskList);
        assertEquals(0, taskList.size());
    }
}
