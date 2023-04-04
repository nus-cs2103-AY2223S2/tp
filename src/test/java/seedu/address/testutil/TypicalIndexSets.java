package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Set<Index>} objects to be used in tests.
 */
public class TypicalIndexSets {

    public static final Set<Index> INDEX_SET_NO_EVENT = new HashSet<>();
    public static final Set<Index> INDEX_SET_FIRST_EVENT = set(Index.fromOneBased(1));
    public static final Set<Index> INDEX_SET_SECOND_EVENT = set(Index.fromOneBased(2));
    public static final Set<Index> INDEX_SET_THIRD_EVENT = set(Index.fromOneBased(3));

    public static final Set<Index> INDEX_SET_FIRST_TWO_EVENT = set2(Index.fromOneBased(1), Index.fromOneBased(2));

    public static Set<Index> set(Index index) {
        Set<Index> set = new HashSet<>();
        set.add(index);
        return set;
    }

    public static Set<Index> set2(Index index1, Index index2) {
        Set<Index> set = new HashSet<>();
        set.add(index1);
        set.add(index2);
        return set;
    }


}
