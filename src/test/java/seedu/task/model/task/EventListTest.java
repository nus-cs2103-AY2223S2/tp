package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.TypicalDeadlines.PROJECT;
import static seedu.task.testutil.TypicalEvents.EXAM;
import static seedu.task.testutil.TypicalEvents.REST;
import static seedu.task.testutil.TypicalEvents.SLEEPOVER;
import static seedu.task.testutil.TypicalEvents.getTypicalEventsInApril;
import static seedu.task.testutil.TypicalLocalDates.APR26;
import static seedu.task.testutil.TypicalLocalDates.APR30;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_FIFTH;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_THIRD;
import static seedu.task.testutil.TypicalMixedClassList.getTypicalMixedClassList;
import static seedu.task.testutil.TypicalTasks.ALICE;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventListTest {

    @Test
    public void constructor_mixedInputs_onlyAddEvents() {
        ObservableList allTaskList = FXCollections.observableList(getTypicalMixedClassList());
        EventList actual = new EventList(allTaskList, LocalDate.parse("2023-04-26"));

        ObservableList eventList = FXCollections.observableList(getTypicalEventsInApril());
        EventList expected = new EventList(eventList, LocalDate.parse("2023-04-26"));
        assertEquals(actual, expected);
    }

    @Test
    public void size() {
        ObservableList eventList = FXCollections.observableList(getTypicalEventsInApril());
        EventList list = new EventList(eventList, LocalDate.parse("2023-04-26"));
        assertEquals(2, list.size());
    }

    @Test
    public void filter_test() {
        EventList list = new EventList();

        // Select SimpleTasks -> return false
        assertFalse(list.isCorrectType(ALICE, LOCAL_DATE_THIRD));

        // Select Deadline -> return false
        assertFalse(list.isCorrectType(PROJECT, LOCAL_DATE_FIFTH));

        // Select Event -> return true
        assertTrue(list.isCorrectType(SLEEPOVER, APR30));
        assertTrue(list.isCorrectType(EXAM, APR26));

        // Select Event, date is outside of event dates, validation is done during scheduling -> return true
        assertTrue(list.isCorrectType(REST, APR30));
    }

    @Test
    public void equals() {
        ObservableList list1 = FXCollections.observableList(getTypicalEventsInApril());
        EventList e1 = new EventList(list1, LocalDate.parse("2023-04-26"));
        EventList e2 = new EventList(list1, LocalDate.parse("2023-04-26"));
        ObservableList list2 = FXCollections.observableList(new ArrayList<Task>());
        DeadlineList e3 = new DeadlineList(list2, LocalDate.parse("2023-04-26"));
        DeadlineList e4 = new DeadlineList(list2, LocalDate.parse("2023-04-28"));

        // same instance -> return true
        assertTrue(e1.equals(e1));

        // same values -> return true
        assertTrue(e1.equals(e2));

        // different list -> return false
        assertFalse(e3.equals(e2));

        // different dates, same list -> return true
        assertTrue(e3.equals(e4));

        // null -> return false
        assertFalse(e4.equals(null));

        // different type -> return false
        assertFalse(e2.equals("ABC"));
    }
}
