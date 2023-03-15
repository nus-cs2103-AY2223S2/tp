package trackr.testutil;

import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_NOT_DONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.model.TaskList;
import trackr.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task SORT_INVENTORY_N = new TaskBuilder().withTaskName("Sort Inventory")
            .withTaskDeadline("01/01/2024")
            .withTaskStatus("N").build();

    public static final Task THROW_EXPIRED_N = new TaskBuilder().withTaskName("Throw Expired Food")
            .withTaskDeadline("01/01/2024")
            .withTaskStatus("D").build();

    public static final Task BUY_EGGS_D = new TaskBuilder().withTaskName("Buy 5kg of Eggs")
            .withTaskDeadline("11/10/2023")
            .withTaskStatus("D").build();

    // Manually added

    public static final Task CLEAN_TOOLS_N = new TaskBuilder().withTaskName("Clean Tools")
            .withTaskDeadline("03/04/2023")
            .withTaskStatus("N")
            .build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task BUY_FLOUR_N = new TaskBuilder().withTaskName(VALID_TASK_NAME_BUY_FLOUR)
            .withTaskDeadline(VALID_TASK_DEADLINE_2100)
            .withTaskStatus(VALID_TASK_STATUS_NOT_DONE)
            .build();

    public static final String KEYWORD_MATCHING_BUY = "Buy"; // A keyword that matches BUY

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskList} with all the typical tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList taskList = new TaskList();
        for (Task task : getTypicalTasks()) {
            taskList.addTask(task);
        }
        return taskList;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(
                Arrays.asList(SORT_INVENTORY_N, THROW_EXPIRED_N, BUY_EGGS_D));
    }
}
