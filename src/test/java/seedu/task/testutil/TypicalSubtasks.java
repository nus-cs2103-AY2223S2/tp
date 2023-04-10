package seedu.task.testutil;

import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_NAME_BOB;

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

    public static final Subtask BOB_SUBTASK = new SubtaskBuilder().withName(VALID_NAME_BOB)
        .withDescription(VALID_DESCRIPTION_BOB).build();

}
