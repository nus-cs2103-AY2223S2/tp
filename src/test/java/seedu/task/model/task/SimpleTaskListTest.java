package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.TypicalDeadlines.PROJECT;
import static seedu.task.testutil.TypicalEvents.MEETING;
import static seedu.task.testutil.TypicalLocalDates.LOCAL_DATE_THIRD;
import static seedu.task.testutil.TypicalTasks.ALICE;
import static seedu.task.testutil.TypicalTasks.getTypicalTasks;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SimpleTaskListTest {

    @Test
    public void constructor_mixedInputs_onlyAddSimpleTasks() {
        ObservableList allTaskList = FXCollections.observableList(getTypicalTasks());
        SimpleTaskList actual = new SimpleTaskList(allTaskList, LocalDate.parse("2023-04-26"));

        ObservableList simpleTaskList = FXCollections.observableList(getTypicalTasks());
        SimpleTaskList expected = new SimpleTaskList(simpleTaskList, LocalDate.parse("2023-04-26"));
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void size() {
        ObservableList taskList = FXCollections.observableList(getTypicalTasks());
        SimpleTaskList list = new SimpleTaskList(taskList, LocalDate.parse("2023-04-26"));
        Assertions.assertEquals(7, list.size());
    }

    @Test
    public void filter_test() {
        SimpleTaskList list = new SimpleTaskList();

        // Select SimpleTasks -> return true
        assertTrue(list.isCorrectType(ALICE, LOCAL_DATE_THIRD));

        // Select Deadline -> return false
        assertFalse(list.isCorrectType(PROJECT, LOCAL_DATE_THIRD));

        // Select Event -> return false
        assertFalse(list.isCorrectType(MEETING, LOCAL_DATE_THIRD));
    }

    @Test
    public void equals() {
        ObservableList list1 = FXCollections.observableList(getTypicalTasks());
        SimpleTaskList t1 = new SimpleTaskList(list1, LocalDate.parse("2023-04-26"));
        SimpleTaskList t2 = new SimpleTaskList(list1, LocalDate.parse("2023-04-26"));
        ObservableList list2 = FXCollections.observableList(new ArrayList<Task>());
        SimpleTaskList t3 = new SimpleTaskList(list2, LocalDate.parse("2023-04-26"));
        SimpleTaskList t4 = new SimpleTaskList(list2, LocalDate.parse("2023-04-28"));

        // same instance -> return true
        assertTrue(t1.equals(t1));

        // same values -> return true
        assertTrue(t1.equals(t2));

        // different list -> return false
        assertFalse(t3.equals(t2));

        // different dates, same list -> return true
        assertTrue(t3.equals(t4));

        // null -> return false
        assertFalse(t4.equals(null));

        // different type -> return false
        assertFalse(t2.equals("ABC"));
    }
}
