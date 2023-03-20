package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;

/**
 * Unmodifiable list of pairs.
 */
public interface ReadOnlyPair {
    /**
     * Returns an unmodifiable view of the pairs list.
     * This list will not contain any duplicate pairs.
     *
     * @return Unmodifiable pair list.
     */
    ObservableList<Pair> getPairList();
}
