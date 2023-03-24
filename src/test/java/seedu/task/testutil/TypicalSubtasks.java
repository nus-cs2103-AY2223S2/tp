package seedu.task.testutil;

import seedu.task.model.task.Subtask;

/**
 * A utility class containing a list of {@code Subtask} objects to be used in tests.
 */
public class TypicalSubtasks {

    public static final Subtask ALICE_HOMEWORK = new SubtaskBuilder().withName("Homework")
        .withDescription("lecture 1")
        .build();

    public static final Subtask AMY_LAB = new SubtaskBuilder().withName("Lab")
        .withDescription("lecture 2")
        .build();

}
