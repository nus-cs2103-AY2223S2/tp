package vimification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import vimification.model.task.Task;

public class LogicTaskListTest {
    @Test
    public void testSimpleConstructor() {
        LogicTaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
        assertEquals(taskList.size(), 0);
    }

    @Test
    public void testAdvancedConstructor() {
        List<Task> tasks = List.of(new Task("Buy milk"), new Task("Do essay"));
        LogicTaskList taskList = new TaskList(tasks);
        assertFalse(taskList.isEmpty());
        assertEquals(taskList.size(), 2);

        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(taskList.get(i), tasks.get(i));
        }
    }

    @Test
    public void testGet() {
        LogicTaskList taskList = new TaskList();
        taskList.add(new Task("Buy milk"));
        taskList.add(new Task("Do essay"));
        assertEquals(taskList.size(), 2);
        assertFalse(taskList.isEmpty());

        assertEquals(taskList.get(0), new Task("Buy milk"));
        assertNotEquals(taskList.get(1), new Task("Buy milk"));
        assertEquals(taskList.get(1), new Task("Do essay"));
        assertNotEquals(taskList.get(0), new Task("Do essay"));
    }

    @Test
    public void testIsEmpty() {
        LogicTaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
        taskList.add(new Task("Buy milk"));
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void testSize() {
        LogicTaskList taskList = new TaskList();
        assertEquals(taskList.size(), 0);
        taskList.add(new Task("Buy milk"));
        assertEquals(taskList.size(), 1);
        taskList.add(new Task("Do essay"));
        assertEquals(taskList.size(), 2);
    }

    @Test
    public void testSet() {
        LogicTaskList taskList = new TaskList();
        taskList.add(new Task("Buy milk"));
        assertEquals(taskList.size(), 1);

        taskList.set(0, new Task("Buy groceries"));
        assertNotEquals(taskList.get(0), new Task("Buy milk"));
        assertEquals(taskList.get(0), new Task("Buy groceries"));
        assertEquals(taskList.size(), 1);
    }

    @Test
    public void testAdd() {
        LogicTaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
        assertTrue(taskList.size() == 0);

        taskList.add(new Task("Buy milk"));
        assertEquals(taskList.get(0), new Task("Buy milk"));
        assertFalse(taskList.isEmpty());
        assertEquals(taskList.size(), 1);
    }

    @Test
    public void testRemove() {
        LogicTaskList taskList = new TaskList();
        taskList.add(new Task("Buy milk"));
        taskList.add(new Task("Do essay"));
        assertEquals(taskList.size(), 2);
        assertFalse(taskList.isEmpty());

        Task removedTask = taskList.remove(0);
        assertEquals(removedTask, new Task("Buy milk"));
        assertEquals(taskList.size(), 1);
        assertFalse(taskList.isEmpty());
        assertEquals(taskList.get(0), new Task("Do essay"));

        removedTask = taskList.remove(0);
        assertEquals(removedTask, new Task("Do essay"));
        assertEquals(taskList.size(), 0);
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testAddBySpeciicIndex() {
        LogicTaskList taskList = new TaskList();
        taskList.add(new Task("Buy milk"));
        taskList.add(new Task("Do essay"));
        assertEquals(taskList.size(), 2);
        assertFalse(taskList.isEmpty());

        taskList.add(1, new Task("Buy groceries"));
        assertEquals(taskList.get(0), new Task("Buy milk"));
        assertNotEquals(taskList.get(1), new Task("Do essay"));
        assertEquals(taskList.get(1), new Task("Buy groceries"));
        assertEquals(taskList.get(2), new Task("Do essay"));
        assertEquals(taskList.size(), 3);
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        LogicTaskList taskList = new TaskList();
        taskList.add(new Task("Buy milk"));
        taskList.add(new Task("Do essay"));
        assertEquals(taskList.size(), 2);
        assertFalse(taskList.isEmpty());

        Task removedTask = taskList.removeLast();
        assertEquals(removedTask, new Task("Do essay"));
        assertNotEquals(removedTask, new Task("Buy milk"));
        assertEquals(taskList.size(), 1);
        assertFalse(taskList.isEmpty());
        assertEquals(taskList.get(0), new Task("Buy milk"));
        assertNotEquals(taskList.get(0), new Task("Do essay"));
    }
}
