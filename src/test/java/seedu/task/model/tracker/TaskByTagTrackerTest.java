package seedu.task.model.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.task.testutil.TypicalTasks.ALICE;
import static seedu.task.testutil.TypicalTasks.BENSON;
import static seedu.task.testutil.TypicalTasks.CARL;
import static seedu.task.testutil.TypicalTasks.DANIEL;

import org.junit.jupiter.api.Test;

import seedu.task.model.ModelManager;

public class TaskByTagTrackerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void sort_emptyModel_emptyString() {
        assertEquals("", TaskByTagTracker.sort(modelManager));
    }

    @Test
    public void sort_populatedModel_sorted() {
        modelManager.addTask(ALICE);
        modelManager.addTask(BENSON);
        modelManager.addTask(CARL);
        modelManager.addTask(DANIEL);
        assertEquals("1. [friends] (count: 3)\n2. [owesMoney] (count: 1)\n",
                TaskByTagTracker.sort(modelManager));
    }
}
