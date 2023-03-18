package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.EventTask;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalEventTasks {

    public static final EventTask FIRST = new EventTaskBuilder().withTaskDescription("send out survey").build();
    public static final EventTask SECOND = new EventTaskBuilder().withTaskDescription("eat berries").build();
    public static final EventTask THIRD = new EventTaskBuilder().withTaskDescription("eat salmon").build();

    private TypicalEventTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Event Tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (EventTask task : getTypicalEventTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<EventTask> getTypicalEventTasks() {
        return new ArrayList<>(Arrays.asList(FIRST, SECOND, THIRD));
    }
}

