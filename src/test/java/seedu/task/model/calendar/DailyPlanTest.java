package seedu.task.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.TypicalEvents.EXAM;
import static seedu.task.testutil.TypicalEvents.SLEEPOVER;
import static seedu.task.testutil.TypicalEvents.STUDY;
import static seedu.task.testutil.TypicalLocalDates.APR27;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_FIRST;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_FOURTH;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_SECOND;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_THIRD;
import static seedu.task.testutil.TypicalLocalDates.MAY5;
import static seedu.task.testutil.TypicalTasks.AMY;
import static seedu.task.testutil.TypicalTasks.BENSON;
import static seedu.task.testutil.TypicalTasks.BOB;
import static seedu.task.testutil.TypicalTasks.GEORGE;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.model.task.EventList;
import seedu.task.model.task.Task;
import seedu.task.testutil.TypicalEvents;

public class DailyPlanTest {

    @Test
    public void allocateEvent() {
        ObservableList obList = FXCollections.observableList(TypicalEvents.getTypicalEventsInApril());
        EventList taskList = new EventList(obList, LocalDate.parse("2023-04-26"));

        // Event happening on date -> add to date
        DailyPlan actualPlan = new DailyPlan(24, APR27);
        actualPlan.allocateEvent(taskList);

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(SLEEPOVER);
        DailyPlan expectedPlan = new DailyPlan(tasks, 24, 1, APR27);
        assertEquals(expectedPlan, actualPlan);


        // No event happening on date -> return false
        DailyPlan p2 = new DailyPlan(24, MAY5);
        tasks = new ArrayList<>();
        expectedPlan = new DailyPlan(tasks, 24, 0, MAY5);
        actualPlan = new DailyPlan(24, MAY5);
        actualPlan.allocateEvent(taskList);
        assertEquals(expectedPlan, actualPlan);
    }

    @Test
    public void hasRemoved() {
        DailyPlan p1 = new DailyPlan(30, LOCAL_DATE_FOURTH);
        p1.addTask(AMY);
        p1.addTask(BENSON);
        p1.addTask(BOB);

        // found task to remove -> return true
        assertTrue(p1.hasRemoved(AMY));

        // task not found -> return false
        assertFalse(p1.hasRemoved(GEORGE));
    }

    @Test
    public void hasEdited() {
        DailyPlan p1 = new DailyPlan(30, LOCAL_DATE_FOURTH);
        p1.addTask(AMY);
        p1.addTask(BENSON);
        p1.addTask(BOB);

        // found task, edit it -> return true
        assertTrue(p1.hasEdited(AMY, GEORGE));

        // task not found -> return false
        assertFalse(p1.hasEdited(AMY, BOB));
    }

    @Test
    public void getCurrentWorkload() {
        DailyPlan p1 = new DailyPlan(30, LOCAL_DATE_FOURTH);

        p1.addTask(AMY);
        assertEquals(24, p1.getCurrentWorkload());

        p1.addTask(BENSON);
        assertEquals(28, p1.getCurrentWorkload());

        p1.addTask(BOB);
        assertEquals(52, p1.getCurrentWorkload());
    }

    @Test
    public void getSpareTime() {
        DailyPlan p1 = new DailyPlan(30, LOCAL_DATE_FOURTH);

        p1.addTask(AMY);

        // Still have spare time
        assertEquals(6, p1.getSpareTime());

        DailyPlan p2 = new DailyPlan(3, LOCAL_DATE_FOURTH);

        p2.addTask(AMY);
        p2.addTask(BOB);

        // Overloaded day
        assertEquals(-45, p2.getSpareTime());
    }

    @Test
    public void isWithinRange() {
        DailyPlan p1 = new DailyPlan(5, LOCAL_DATE_FOURTH);
        DailyPlan p2 = new DailyPlan(2, LOCAL_DATE_THIRD);

        // same day -> return true
        assertTrue(p1.isWithinRange(EXAM));

        // within day range -> return true
        assertTrue(p1.isWithinRange(SLEEPOVER));

        // after day -> return false
        assertFalse(p2.isWithinRange(EXAM));

        // before day -> return false
        assertFalse(p1.isWithinRange(STUDY));
    }

    @Test
    public void hasSpareTime() {
        DailyPlan p1 = new DailyPlan(5, LOCAL_DATE_FIRST);

        // work longer than desired -> return false
        assertFalse(p1.hasSpareTime(10));

        // work shorter than desired -> return true;
        assertTrue(p1.hasSpareTime(2));

        // work does not take time -> return true
        assertTrue(p1.hasSpareTime(0));
    }

    @Test
    public void equals_test() {
        DailyPlan p1 = new DailyPlan(5, LOCAL_DATE_FIRST);
        DailyPlan p2 = new DailyPlan(5, LOCAL_DATE_FIRST);
        DailyPlan p3 = new DailyPlan(5, LOCAL_DATE_SECOND);
        DailyPlan p4 = new DailyPlan(10, LOCAL_DATE_SECOND);

        // same object -> return true
        assertTrue(p1.equals(p1));

        // different object, same field -> return true
        assertTrue(p1.equals(p2));

        // same workload, different date -> return false
        assertFalse(p1.equals(p3));

        // same date, different workload -> return false
        assertFalse(p3.equals(p4));

        // different date and workload -> return false
        assertFalse(p2.equals(p3));

        // null -> return false
        assertFalse(p1.equals(null));

        ArrayList taskList = new ArrayList();
        DailyPlan p5 = new DailyPlan(taskList, 10, 10, LocalDate.parse("2023-03-23"));
        DailyPlan p6 = new DailyPlan(taskList, 10, 10, LocalDate.parse("2023-03-23"));
        DailyPlan p7 = new DailyPlan(taskList, 10, 10, LocalDate.parse("2023-03-24"));
        DailyPlan p8 = new DailyPlan(taskList, 5, 10, LocalDate.parse("2023-03-24"));

        // same values from Json -> return true
        assertTrue(p5.equals(p6));

        // different values from Json -> return false
        assertFalse(p5.equals(p7));
        assertFalse(p8.equals(p7));
    }
}
