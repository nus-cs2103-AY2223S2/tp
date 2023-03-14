package seedu.task.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_FIRST;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_FOURTH;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_SECOND;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_THIRD;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.TypicalEvents;
import seedu.task.testutil.TypicalTasks;

public class DailyPlanTest {

    @Test
    public void getCurrentWorkload_test() {
        DailyPlan p1 = new DailyPlan(8, LOCAL_DATE_FOURTH);

        p1.addTask(TypicalTasks.AMY);
        assertEquals(2, p1.getCurrentWorkload());

        p1.addTask(TypicalTasks.BENSON);
        assertEquals(6, p1.getCurrentWorkload());

        p1.addTask(TypicalTasks.BOB);
        assertEquals(8, p1.getCurrentWorkload());
    }

    @Test
    public void getSpareTime_test() {
        DailyPlan p1 = new DailyPlan(8, LOCAL_DATE_FOURTH);

        p1.addTask(TypicalTasks.AMY);

        // Still have spare time
        assertEquals(6, p1.getSpareTime());

        DailyPlan p2 = new DailyPlan(3, LOCAL_DATE_FOURTH);

        p2.addTask(TypicalTasks.AMY);
        p2.addTask(TypicalTasks.BOB);

        // Overloaded day
        assertEquals(-1, p2.getSpareTime());
    }

    @Test
    public void isWithinRange_test() {
        DailyPlan p1 = new DailyPlan(5, LOCAL_DATE_FOURTH);
        DailyPlan p2 = new DailyPlan(2, LOCAL_DATE_THIRD);

        // same day -> return true
        assertTrue(p1.isWithinRange(TypicalEvents.EXAM));

        // within day range -> return true
        assertTrue(p1.isWithinRange(TypicalEvents.SLEEPOVER));

        // after day -> return false
        assertFalse(p2.isWithinRange(TypicalEvents.EXAM));

        // before day -> return false
        assertFalse(p1.isWithinRange(TypicalEvents.STUDY));
    }

    @Test
    public void hasSpareTime_test() {
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
    }
}
