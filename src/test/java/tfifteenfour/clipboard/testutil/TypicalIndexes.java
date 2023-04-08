package tfifteenfour.clipboard.testutil;

import tfifteenfour.clipboard.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST = Index.fromOneBased(1);
    public static final Index INDEX_SECOND = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Index INDEX_OUT_OF_BOUND = Index.fromOneBased(100);

}
