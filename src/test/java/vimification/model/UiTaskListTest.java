package vimification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import vimification.model.task.Task;

public class UiTaskListTest {
    @Test
    public void testPredicate() {
        UiTaskList utl = new TaskList();
        Predicate<Task> expectedPred = task -> task.getTitle().equals("Buy");

        utl.setPredicate(expectedPred);
        Predicate<? super Task> actualPred = utl.getPredicate();

        Task task1 = new Task("Buy milk");
        Task task2 = new Task("Do essay");
        assertEquals(actualPred.test(task1), expectedPred.test(task1));
        assertEquals(actualPred.test(task2), expectedPred.test(task2));
    }

    @Test
    public void testComparator() {
        UiTaskList utl = new TaskList();
        Comparator<Task> expectedCmp = (
                task1, task2) -> task2.getTitle().compareTo(task1.getTitle());

        utl.setComparator(expectedCmp);
        Comparator<? super Task> actualCmp = utl.getComparator();

        Task task1 = new Task("Buy milk");
        Task task2 = new Task("Do essay");
        assertEquals(expectedCmp.compare(task1, task2), actualCmp.compare(task1, task2));
    }
}
