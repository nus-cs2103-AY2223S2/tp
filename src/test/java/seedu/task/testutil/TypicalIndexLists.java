package seedu.task.testutil;

import seedu.task.commons.core.index.IndexList;

/**
 * A utility class containing a list of {@code IndexList} objects to be used in tests.
 */
public class TypicalIndexLists {
    public static final int[] A = {1};

    public static final String INDEXLIST_FIRST_TASK_INT = "1";
    public static final int[] B = {1, 2};

    public static final IndexList INDEXLIST_FIRST_TASK = IndexList.fromOneBasedList(A);
    public static final IndexList INDEXLIST_SECOND_TASK = IndexList.fromOneBasedList(B);
}
