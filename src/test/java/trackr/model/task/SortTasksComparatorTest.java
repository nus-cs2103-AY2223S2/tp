package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import trackr.logic.parser.CriteriaEnum;
import trackr.testutil.TaskBuilder;

public class SortTasksComparatorTest {

    @Test
    public void compareTimeAdded() {
        SortTasksComparator comparator = new SortTasksComparator();
        comparator.setCriteria(CriteriaEnum.TIME_ADDED);

        LocalDateTime currTime = LocalDateTime.now();
        Task task1 = new TaskBuilder(SORT_INVENTORY_N).withTimeAdded(currTime).build();
        Task task2 = new TaskBuilder(SORT_INVENTORY_N).withTimeAdded(currTime.minusDays(1)).build();

        assertEquals(0, comparator.compare(task1, task1));
        assertEquals(1, comparator.compare(task1, task2));
        assertEquals(-1, comparator.compare(task2, task1));
    }

    @Test
    public void compareDeadline() {
        SortTasksComparator comparator = new SortTasksComparator();
        comparator.setCriteria(CriteriaEnum.DEADLINE);

        Task task1 = new TaskBuilder(SORT_INVENTORY_N).withTaskDeadline("01/01/2023").build();
        Task task2 = new TaskBuilder(SORT_INVENTORY_N).withTaskDeadline("01/01/2020").build();

        assertEquals(0, comparator.compare(task1, task1));
        assertEquals(1, comparator.compare(task1, task2));
        assertEquals(-1, comparator.compare(task2, task1));
    }

    @Test
    public void compareStatus() {
        SortTasksComparator comparator = new SortTasksComparator();
        comparator.setCriteria(CriteriaEnum.STATUS);

        Task task1 = new TaskBuilder(SORT_INVENTORY_N).withTaskStatus("D").build();
        Task task2 = new TaskBuilder(SORT_INVENTORY_N).withTaskStatus("N").build();

        assertEquals(0, comparator.compare(task1, task1));

        assertEquals(1, comparator.compare(task1, task2));
        assertEquals(-1, comparator.compare(task2, task1));
    }

    @Test
    public void compareName() {
        SortTasksComparator comparator = new SortTasksComparator();
        comparator.setCriteria(CriteriaEnum.NAME);

        Task task1 = new TaskBuilder(SORT_INVENTORY_N)
                             .withTaskName("abc")
                             .build();
        Task task2 = new TaskBuilder(SORT_INVENTORY_N)
                             .withTaskName("XYZ")
                             .build();

        assertEquals(0, comparator.compare(task1, task1));
        assertEquals(-1, comparator.compare(task1, task2));
        assertEquals(1, comparator.compare(task2, task1));
    }

    @Test
    public void compareStatusAndDeadline() {
        SortTasksComparator comparator = new SortTasksComparator();
        comparator.setCriteria(CriteriaEnum.STATUS_AND_DEADLINE);

        Task task1 = new TaskBuilder(SORT_INVENTORY_N).withTaskStatus("D").build();
        Task task2 = new TaskBuilder(SORT_INVENTORY_N).withTaskStatus("N").build();

        assertEquals(0, comparator.compare(task1, task1));

        // Comparison of different status, same deadline
        assertEquals(1, comparator.compare(task1, task2));
        assertEquals(-1, comparator.compare(task2, task1));

        // Comparison of same status, different deadline
        task1 = new TaskBuilder(SORT_INVENTORY_N).withTaskDeadline("01/01/2023").build();
        task2 = new TaskBuilder(SORT_INVENTORY_N).withTaskDeadline("01/01/2020").build();

        assertEquals(1, comparator.compare(task1, task2));
        assertEquals(-1, comparator.compare(task2, task1));

        // Comparison of different status, different deadline
        task1 = new TaskBuilder(SORT_INVENTORY_N).withTaskStatus("D").withTaskDeadline("01/01/2023").build();
        task2 = new TaskBuilder(SORT_INVENTORY_N).withTaskStatus("N").withTaskDeadline("01/01/2020").build();

        assertEquals(1, comparator.compare(task1, task2));
        assertEquals(-1, comparator.compare(task2, task1));
    }

}
