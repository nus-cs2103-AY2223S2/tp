package vimification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Utility class for testing.
 */
public class TestUtil {

    private TestUtil() {}

    public static Task newTask() {
        return new Task("Touhou Project");
    }

    /**
     * Returns a stub {@code CommandStack} with the specified {@code tasks}.
    */
    public static LogicTaskList newLogicTaskListStub(Task... tasks) {
        LogicTaskList taskList = new LogicTaskList() {
            private List<Task> tasks = new ArrayList<>();

            @Override
            public boolean isEmpty() {
                return tasks.isEmpty();
            }

            @Override
            public int size() {
                return tasks.size();
            }

            @Override
            public void add(Task task) {
                tasks.add(task);
            }

            @Override
            public void add(int index, Task task) {
                tasks.add(index, task);
            }

            @Override
            public void set(int index, Task task) {
                tasks.set(index, task);
            }

            @Override
            public Task remove(int index) {
                return tasks.remove(index);
            }

            @Override
            public Task removeLast() {
                return tasks.remove(tasks.size() - 1);
            }

            @Override
            public Task get(int index) {
                return tasks.get(index);
            }

            @Override
            public List<Task> getLogicSource() {
                return tasks;
            }

            @Override
            public int getLogicSourceIndex(int index) {
                return index;
            }
        };
        Arrays.stream(tasks).forEach(taskList::add);
        return taskList;
    }

    public static CommandStack newCommandStack() {
        return new CommandStack();
    }
}
