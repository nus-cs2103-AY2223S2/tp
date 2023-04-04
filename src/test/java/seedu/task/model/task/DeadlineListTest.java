package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.TypicalDeadlines.PROJECT;
import static seedu.task.testutil.TypicalDeadlines.getTypicalDeadlinesInApril;
import static seedu.task.testutil.TypicalEvents.MEETING;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_FIFTH;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_THIRD;
import static seedu.task.testutil.TypicalMixedClassList.getTypicalMixedClassList;
import static seedu.task.testutil.TypicalTasks.ALICE;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeadlineListTest {
    @Test
    public void constructor_mixedInputs_onlyAddDeadlines() {
        ObservableList allTaskList = FXCollections.observableList(getTypicalMixedClassList());
        DeadlineList actual = new DeadlineList(allTaskList, LocalDate.parse("2023-04-26"));

        ObservableList deadlineList = FXCollections.observableList(getTypicalDeadlinesInApril());
        DeadlineList expected = new DeadlineList(deadlineList, LocalDate.parse("2023-04-26"));
        assertEquals(actual, expected);
    }

    @Test
    public void size() {
        ObservableList deadlineList = FXCollections.observableList(getTypicalDeadlinesInApril());
        DeadlineList list = new DeadlineList(deadlineList, LocalDate.parse("2023-04-26"));
        assertEquals(2, list.size());
    }

    @Test
    public void filter() {
        DeadlineList list = new DeadlineList();

        // Select SimpleTasks -> return false
        assertFalse(list.isCorrectType(ALICE, LOCAL_DATE_THIRD));

        // Select Deadline, Before due date -> return true
        assertTrue(list.isCorrectType(PROJECT, LOCAL_DATE_FIFTH));

        //Select Deadline, after due date -> return false
        assertFalse(list.isCorrectType(PROJECT, LOCAL_DATE_THIRD));

        // Select Event -> return false
        assertFalse(list.isCorrectType(MEETING, LOCAL_DATE_THIRD));
    }

    @Test
    public void equals() {
        ObservableList list1 = FXCollections.observableList(getTypicalDeadlinesInApril());
        DeadlineList d1 = new DeadlineList(list1, LocalDate.parse("2023-04-26"));
        DeadlineList d2 = new DeadlineList(list1, LocalDate.parse("2023-04-26"));
        ObservableList list2 = FXCollections.observableList(new ArrayList<Task>());
        DeadlineList d3 = new DeadlineList(list2, LocalDate.parse("2023-04-26"));
        DeadlineList d4 = new DeadlineList(list2, LocalDate.parse("2023-04-28"));

        // same instance -> return true
        assertTrue(d1.equals(d1));

        // same values -> return true
        assertTrue(d1.equals(d2));

        // different list -> return false
        assertFalse(d3.equals(d2));

        // different dates, same list -> return true
        assertTrue(d3.equals(d4));

        // null -> return false
        assertFalse(d4.equals(null));

        // different type -> return false
        assertFalse(d4.equals("ABC"));
    }
}
