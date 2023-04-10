package vimification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vimification.model.task.Task;

import java.util.List;

public class TaskListTest {
    @Test
    public void testSimpleConstructor() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
        assertEquals(taskList.size(), 0);
    }

    @Test
    public void testAdvancedConstructor() {
        List<Task> tasks = List.of(new Task("Buy milk"), new Task("Do essay"));
        TaskList taskList = new TaskList(tasks);
        assertFalse(taskList.isEmpty());
        assertEquals(taskList.size(), 2);

        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(taskList.get(i), tasks.get(i));
        }
    }
}
