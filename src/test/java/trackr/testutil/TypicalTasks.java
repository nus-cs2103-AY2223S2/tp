package trackr.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.model.TaskList;
import trackr.model.task.Task;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task SORT_INVENTORY_N = new TaskBuilder().withTaskName("Sort Inventory")
            .withTaskDeadline(LocalDate.parse("2024-01-01"))
            .withTaskStatus("N").build();

    public static final Task SORT_INVENTORY_D = new TaskBuilder().withTaskName("Sort Inventory")
            .withTaskDeadline(LocalDate.parse("2024-01-01"))
            .withTaskStatus("D").build();

    public static final Task SORT_INVENTORY_DIFF_DATE_N = new TaskBuilder().withTaskName("Sort Inventory")
            .withTaskDeadline(LocalDate.parse("2023-12-15"))
            .withTaskStatus("N").build();

    public static final Task SORT_INVENTORY_DIFF_DATE_D = new TaskBuilder().withTaskName("Sort Inventory")
            .withTaskDeadline(LocalDate.parse("2023-12-15"))
            .withTaskStatus("D").build();

    public static final Task BUY_FLOUR_N = new TaskBuilder().withTaskName("Buy 10kg of Flour")
            .withTaskDeadline(LocalDate.parse("2023-09-01"))
            .withTaskStatus("N").build();

    public static final Task BUY_EGGS_D = new TaskBuilder().withTaskName("Buy 5kg of Eggs")
            .withTaskDeadline(LocalDate.parse("2023-10-11"))
            .withTaskStatus("D").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    /*public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();*/

    public static final String KEYWORD_MATCHING_BUY = "Buy"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskList} with all the typical persons.
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
                Arrays.asList(SORT_INVENTORY_N, SORT_INVENTORY_D, SORT_INVENTORY_DIFF_DATE_N,
                        SORT_INVENTORY_DIFF_DATE_D, BUY_FLOUR_N, BUY_EGGS_D));
    }
}
