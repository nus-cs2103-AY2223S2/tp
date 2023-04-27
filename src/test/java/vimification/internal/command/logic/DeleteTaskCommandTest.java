package vimification.internal.command.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import vimification.TestUtil;
import vimification.common.core.Index;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class DeleteTaskCommandTest {

    @Test
    public void shouldWorkCorrectly() {
        Task task = TestUtil.newTask();
        LogicTaskList taskList = TestUtil.newLogicTaskListStub(task);
        CommandStack commandStack = TestUtil.newCommandStack();

        DeleteTaskCommand command = new DeleteTaskCommand(Index.fromOneBased(1));
        command.execute(taskList, commandStack);

        assertEquals(0, taskList.size());
        assertEquals(1, commandStack.size());
        assertEquals(command, commandStack.pop());

        command.undo(taskList);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }
}
