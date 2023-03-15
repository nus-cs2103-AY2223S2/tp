package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.DeadlineTask;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalDeadlineTasks {

    public static final DeadlineTask FIRST = new DeadlineTaskBuilder().withTaskDescription("send out survey").build();
    public static final DeadlineTask SECOND = new DeadlineTaskBuilder().withTaskDescription("eat berries").build();
    public static final DeadlineTask THIRD = new DeadlineTaskBuilder().withTaskDescription("eat salmon").build();

    private TypicalDeadlineTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (DeadlineTask task : getTypicalDeadlineTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<DeadlineTask> getTypicalDeadlineTasks() {
        return new ArrayList<>(Arrays.asList(FIRST, SECOND, THIRD));
    }
}
