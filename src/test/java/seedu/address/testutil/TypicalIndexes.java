package seedu.address.testutil;

import java.util.ArrayList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.listing.Listing;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_LISTING = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_LISTING = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_LISTING = Index.fromOneBased(3);
    public static final Index getIndexLastListing(ArrayList<Listing> listing) {
        return Index.fromOneBased(listing.size());
    }
}
