package vimification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Predicate;

import vimification.model.task.Task;

public class UiTaskListTest {
    @Test
    public void testPredicate() {
        UiTaskList utl = new TaskList();
        Predicate<Task> pred = task -> task.getTitle().equals("Buy");
        utl.setPredicate(pred);
        assertEquals(utl.getPredicate(), pred);
    }

    @Test
    public void testComparator() {
        UiTaskList utl = new TaskList();
        Comparator<Task> cmp = (task1, task2) -> task2.getTitle().compareTo(task1.getTitle());
        utl.setComparator(cmp);
        assertEquals(utl.getPredicate(), cmp);
    }
}
