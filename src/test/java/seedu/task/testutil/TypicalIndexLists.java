package seedu.task.testutil;

import seedu.task.commons.core.index.IndexList;

/**
 * A utility class containing a list of {@code IndexList} objects to be used in tests.
 */
public class TypicalIndexLists {
    public static int[] a = {1};

    public static String INDEXLIST_FIRST_TASK_INT = "1";
    public static int[] b = {1, 2};

    public static final IndexList INDEXLIST_FIRST_TASK = IndexList.fromOneBasedList(a);
    public static final IndexList INDEXLIST_SECOND_TASK = IndexList.fromOneBasedList(b);
}
