package seedu.task.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.TypicalDeadlines.getTypicalDeadlinesInApril;
import static seedu.task.testutil.TypicalEvents.getTypicalEventsInApril;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_FOURTH;
import static seedu.task.testutil.TypicalMonthlyPlan.APR26_PLAN;
import static seedu.task.testutil.TypicalMonthlyPlan.DAILY_PLANS_DEADLINE;
import static seedu.task.testutil.TypicalMonthlyPlan.DAILY_PLANS_EVENT;
import static seedu.task.testutil.TypicalMonthlyPlan.DAILY_PLANS_SIMPLE_TASK;
import static seedu.task.testutil.TypicalTasks.getTypicalTasks;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.model.task.DeadlineList;
import seedu.task.model.task.EventList;
import seedu.task.model.task.SimpleTaskList;

public class MonthlyPlanTest {

    @Test
    public void constructorCreation() {
        MonthlyPlan m = new MonthlyPlan(24, LOCAL_DATE_FOURTH);
        MonthlyPlan m2 = APR26_PLAN;
        assertEquals(APR26_PLAN, m);
    }

    @Test
    public void allocateEvents() {
        ObservableList obList = FXCollections.observableList(getTypicalEventsInApril());
        EventList taskList = new EventList(obList, LocalDate.parse("2023-04-26"));

        MonthlyPlan expectedPlan = new MonthlyPlan(DAILY_PLANS_EVENT, LocalDate.parse("2023-04-26"));

        MonthlyPlan actualPlan = new MonthlyPlan(24, LocalDate.parse("2023-04-26"));
        actualPlan.allocateEvents(taskList);

        assertTrue(expectedPlan.equals(actualPlan));
    }

    @Test
    public void allocateDeadlines() {
        ObservableList obList = FXCollections.observableList(getTypicalDeadlinesInApril());
        DeadlineList taskList = new DeadlineList(obList, LocalDate.parse("2023-04-26"));

        MonthlyPlan expectedPlan = new MonthlyPlan(DAILY_PLANS_DEADLINE, LocalDate.parse("2023-04-26"));

        MonthlyPlan actualPlan = new MonthlyPlan(24, LocalDate.parse("2023-04-26"));
        actualPlan.allocateDeadlines(taskList);

        assertTrue(expectedPlan.equals(actualPlan));
    }

    @Test
    public void allocateSimpleTasks() {
        ObservableList obList = FXCollections.observableList(getTypicalTasks());
        SimpleTaskList taskList = new SimpleTaskList(obList, LocalDate.parse("2023-04-26"));

        MonthlyPlan expectedPlan = new MonthlyPlan(DAILY_PLANS_SIMPLE_TASK, LocalDate.parse("2023-04-26"));

        MonthlyPlan actualPlan = new MonthlyPlan(24, LocalDate.parse("2023-04-26"));
        actualPlan.allocateSimpleTasks(taskList);

        assertTrue(expectedPlan.equals(actualPlan));
    }

    @Test
    public void findMostBusyFreeDate() {
        // not yet populated, first free day
        assertEquals(0, APR26_PLAN.findMostBusyFreeDate(0));

        // task requires too much effort -> return -1
        assertEquals(-1, APR26_PLAN.findMostBusyFreeDate(50));
    }

    @Test
    public void findFirstFreeDate() {
        // not yet populated, first free day
        assertEquals(0, APR26_PLAN.findFirstFreeDate(3, 1));

        // no free days before the deadline -> return -1
        assertEquals(-1, APR26_PLAN.findFirstFreeDate(3, -1));
    }

    @Test
    public void equals() {
        MonthlyPlan planA = new MonthlyPlan(10, LocalDate.parse("2023-03-23"));
        MonthlyPlan planB = new MonthlyPlan(10, LocalDate.parse("2023-03-23"));
        MonthlyPlan planC = new MonthlyPlan(15, LocalDate.parse("2023-03-23"));
        MonthlyPlan planD = new MonthlyPlan(10, LocalDate.parse("2023-03-20"));

        // same object -> returns true
        assertTrue(planA.equals(planA));

        // same values -> returns true
        assertTrue(planA.equals(planB));

        // different values -> returns false
        Assertions.assertFalse(planA.equals(planC)); // different workload
        Assertions.assertFalse(planA.equals(planD)); // different date
    }
}
