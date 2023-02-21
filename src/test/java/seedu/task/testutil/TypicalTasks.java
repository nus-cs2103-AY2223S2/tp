package seedu.task.testutil;

import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOTH;
import static seedu.task.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.task.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.task.model.TaskBook;
import seedu.task.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withName("Alice Pauline")
            .withDescription("Alice's description")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withName("Benson Meier")
            .withDescription("Benson's description")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withName("Carl Kurz")
            .withDescription("Carl's description")
            .build();
    public static final Task DANIEL = new TaskBuilder().withName("Daniel Meier")
            .withDescription("Daniel's description")
            .withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withName("Elle Meyer")
            .withDescription("Elle's description")
            .build();
    public static final Task FIONA = new TaskBuilder().withName("Fiona Kunz")
            .withDescription("Fiona's description")
            .build();
    public static final Task GEORGE = new TaskBuilder().withName("George Best")
            .withDescription("George's description")
            .build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withName("Hoon Meier")
            .withDescription("Hoon's description")
            .build();
    public static final Task IDA = new TaskBuilder().withName("Ida Mueller")
            .withDescription("Ida's description")
            .build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY).withDescription(VALID_DESCRIPTION_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB).withDescription(VALID_DESCRIPTION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    public static final Task BOB_BOTH = new TaskBuilder().withName(VALID_NAME_BOB)
        .withDescription(VALID_DESCRIPTION_BOTH)
        .withTags(VALID_TAG_FRIEND)
        .build();
    public static final Task AMY_BOTH = new TaskBuilder().withName(VALID_NAME_AMY)
        .withDescription(VALID_DESCRIPTION_BOTH)
        .withTags(VALID_TAG_FRIEND)
        .build();





    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskBook} with all the typical tasks.
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook ab = new TaskBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
